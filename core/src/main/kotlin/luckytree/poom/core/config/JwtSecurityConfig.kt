package luckytree.poom.core.config

import luckytree.poom.core.filter.JwtFilter
import luckytree.poom.core.service.TokenProvider
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtSecurityConfig(private val tokenProvider: TokenProvider) :
    SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {
    override fun configure(http: HttpSecurity) {
        http.addFilterBefore(
            JwtFilter(tokenProvider),
            UsernamePasswordAuthenticationFilter::class.java
        )
    }
}
