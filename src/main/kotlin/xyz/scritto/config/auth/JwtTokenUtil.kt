package xyz.scritto.config.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenUtil {
    companion object {
        @Value("\${jwt.secret}")
        private lateinit var secret: String
    }

    private val expiration = 6000000

    private val grantedAuthories = AuthorityUtils.commaSeparatedStringToAuthorityList(
        "ROLE_USER"
    )
        .stream()
        .map { it.authority }
        .toList()

    fun generateToken(username: String): String =
        Jwts.builder()
            .setSubject(username)
            .claim("authorities", grantedAuthories)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact()

    private fun getClaims(token: String): Claims =
        Jwts.parser()
            .setSigningKey(secret.toByteArray())
            .parseClaimsJws(token)
            .body

    fun getEmailFromToken(token: String): String =
        getClaims(token).subject

    fun isTokenValid(token: String): Boolean =
        getClaims(token).expiration.after(Date(System.currentTimeMillis()))
};



