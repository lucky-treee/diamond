package luckytree.poom.core.filter

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter

class InternalKeyFilter(
    internalKeyManager: InternalKeyManager,
) : AbstractPreAuthenticatedProcessingFilter() {
    init {
        setAuthenticationManager(internalKeyManager)
    }

    override fun getPreAuthenticatedPrincipal(request: HttpServletRequest): String? = request.getHeader(INTENRAL_KEY)

    override fun getPreAuthenticatedCredentials(request: HttpServletRequest) = "N/A"

    companion object {
        private const val INTENRAL_KEY = "INTERNAL-KEY"
    }
}
