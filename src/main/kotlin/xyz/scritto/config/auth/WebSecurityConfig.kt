package xyz.scritto.config.auth

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import xyz.scritto.service.UserDetailsService

@Configuration
class WebSecurityConfig(
    private val userDetailsService: UserDetailsService
) {
    private val jwtToken = JwtTokenUtil()

    @Bean
    fun authManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder::class.java
        )
        authenticationManagerBuilder.userDetailsService(userDetailsService)

        return authenticationManagerBuilder.build()
    }

    @Bean
    fun provideSecurityChain(
        http: HttpSecurity
    ): SecurityFilterChain {
        val authenticationManager = authManager(http)

        return http
            .authorizeHttpRequests()
            .requestMatchers("/auth/*")
            .permitAll()
            .anyRequest()
            .permitAll()
            .and()
            .csrf()
            .disable()
            .authenticationManager(authenticationManager)
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(JwtAuthenticationFilter(jwtToken, authenticationManager))
            .addFilter(JwtAuthorizationFilter(jwtToken, userDetailsService, authenticationManager))
            .build()
    }

    @Bean
    fun provideBCryptPaasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}