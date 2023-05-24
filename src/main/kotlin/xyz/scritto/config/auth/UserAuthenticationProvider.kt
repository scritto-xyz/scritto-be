package xyz.scritto.config.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import xyz.scritto.dto.auth.LoginDto
import xyz.scritto.service.UsersService
import java.util.Base64
import java.util.Date

@Component
class UserAuthenticationProvider(
    @Value("\${jwt.secret}") private val secret: String,
    private val usersService: UsersService
) {
    private val secretEncoded: String = Base64.getEncoder()
        .encodeToString(secret.toByteArray())


    fun createToken(login: String): String {
        val now = Date()
        val validity = Date(now.time + 3600000) // 1 hour
        val algo = Algorithm.HMAC256(secretEncoded)
        return JWT.create()
            .withIssuer(login)
            .withIssuedAt(Date())
            .withExpiresAt(validity)
            .sign(algo)
    }

    fun validateToken(token: String): Authentication {
        val algo = Algorithm.HMAC256(secretEncoded)
        val verifier = JWT.require(algo)
            .build()
        val decoded = verifier.verify(token)
        val user = usersService.getUserByEmail(decoded.issuer)

        return UsernamePasswordAuthenticationToken(user, null, listOf())
    }

    fun validateCredentials(credentials: LoginDto): Authentication {
        val user = usersService.authenticate(credentials)
        return UsernamePasswordAuthenticationToken(user, null, listOf())
    }
}