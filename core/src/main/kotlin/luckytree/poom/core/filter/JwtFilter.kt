package luckytree.poom.core.filter

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm.HMAC512
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import luckytree.poom.core.config.JwtConfiguration
import luckytree.poom.core.jwt.AuthenticationToken
import luckytree.poom.core.jwt.Claims
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import java.net.URLDecoder
import java.util.regex.Pattern

class JwtFilter(private val jwtConfiguration: JwtConfiguration) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (SecurityContextHolder.getContext().authentication == null) {
            request.getToken()?.let { changeAuthentication(it, request) }
        }

        filterChain.doFilter(request, response)
    }

    private fun HttpServletRequest.getToken(): String? {
        return runCatching {
            val token = getHeader(jwtConfiguration.header).toString()
            val (scheme, credentials) = URLDecoder.decode(token, Charsets.UTF_8).split(" ").let { it.first() to it.last() }

            credentials.takeIf { BEARER.matcher(scheme).matches() }
        }.getOrNull()
    }

    private fun changeAuthentication(token: String, httpServletRequest: HttpServletRequest) {
        with(verify(token)) {
            val principal = memberId
            val authorities = listOf(SimpleGrantedAuthority(role))
            AuthenticationToken(principal = principal, authorities = authorities)
                .apply { details = WebAuthenticationDetailsSource().buildDetails(httpServletRequest) }
                .also {
                    SecurityContextHolder.getContext().authentication = it
                    SecurityContextHolder.getContext().authentication.isAuthenticated = true
                }
        }
    }

    private fun verify(token: String): Claims {
        val algorithm = HMAC512(jwtConfiguration.secret)
        val jwtVerifier = JWT.require(algorithm).build()

        return Claims(jwtVerifier.verify(token))
    }

    companion object {
        private val BEARER = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE)
    }
}
