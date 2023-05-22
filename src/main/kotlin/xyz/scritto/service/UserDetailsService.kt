package xyz.scritto.service

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import xyz.scritto.model.auth.UserSecurity
import xyz.scritto.repository.UsersRepository
import java.util.*

@Service
class UserDetailsService(
    private val usersRepository: UsersRepository,
) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = usersRepository.getUserByEmail(email) ?: throw Exception("User not found")
        return UserSecurity(
            user.id,
            user.email,
            user.password,
            Collections.singleton(SimpleGrantedAuthority("user"))
        )
    }
}