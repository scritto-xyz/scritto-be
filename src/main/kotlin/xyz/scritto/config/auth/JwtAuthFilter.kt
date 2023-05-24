package xyz.scritto.config.auth

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthFilter(private val provider: UserAuthenticationProvider) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader(AUTHORIZATION)

        if (header != null) {
            val authElements = header.split(" ")
            if (authElements.size == 2 && authElements[0] == "Bearer") {
                try {
                    val token = authElements[1]
                    SecurityContextHolder.getContext().authentication = provider.validateToken(token)
                } catch (e: RuntimeException) {
                    SecurityContextHolder.clearContext()
                    throw e
                }
            }
        }
        filterChain.doFilter(request, response)
    }
}