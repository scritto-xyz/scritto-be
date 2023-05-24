package xyz.scritto.config.auth

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userAuthenticationProvider: UserAuthenticationProvider,
) {
    @Bean
    fun provideSecurityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .addFilterBefore(
                UsernamePasswordAuthFilter(userAuthenticationProvider),
                BasicAuthenticationFilter::class.java
            )
            .addFilterBefore(
                JwtAuthFilter(userAuthenticationProvider),
                UsernamePasswordAuthFilter::class.java
            )
            .csrf()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .build()
}