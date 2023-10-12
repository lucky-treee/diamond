package luckytree.poom.core.filter

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

class InternalKeyManager(
    private val internalKeySecret: String,
) : AuthenticationManager {
    override fun authenticate(authentication: Authentication): Authentication {
        if (SecurityContextHolder.getContext().authentication != null) {
            return SecurityContextHolder.getContext().authentication
        }

        if (internalKeySecret != authentication.principal) {
            throw AuthenticationServiceException("failed to authenticate.")
        }

        return authentication.apply { isAuthenticated = true }
    }
}
