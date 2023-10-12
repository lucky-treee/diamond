package luckytree.poom.core.jwt

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class AuthenticationToken(
    private val credentials: String? = null,

    private val principal: String,

    authorities: List<GrantedAuthority>,
) : AbstractAuthenticationToken(authorities) {
    override fun getCredentials() = credentials

    override fun getPrincipal() = principal
}
