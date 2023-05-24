package xyz.scritto.config.auth

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpMethod
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import xyz.scritto.dto.auth.LoginDto

class UsernamePasswordAuthFilter(private val provider: UserAuthenticationProvider) : OncePerRequestFilter() {
    companion object {
        private val MAPPER = ObjectMapper()
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        if (request.servletPath == "/auth/login"
            && HttpMethod.POST.matches(request.method)
        ) {
            val credentials = MAPPER.readValue(request.inputStream, LoginDto::class.java)
            try {
                SecurityContextHolder.getContext().authentication = provider.validateCredentials(credentials)
            } catch (e: RuntimeException) {
                SecurityContextHolder.clearContext()
                throw e
            }
        }
        filterChain.doFilter(request, response)
    }
}