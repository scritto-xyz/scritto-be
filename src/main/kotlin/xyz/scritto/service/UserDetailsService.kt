package xyz.scritto.service

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import xyz.scritto.model.auth.UserSecurity
import xyz.scritto.repository.UsersRepository
import java.util.Collections

@Service
class UserDetailsService(
    private val usersRepository: UsersRepository,
) : UserDetailsService {

    /**
     * in our case we're loading by email
     */
    override fun loadUserByUsername(email: String): UserDetails {
        val user = usersRepository.getUserByEmail(email) ?: throw UsernameNotFoundException("$email not found")
        return UserSecurity(
            user.id,
            user.email,
            user.password,
            Collections.singleton(SimpleGrantedAuthority("user"))
        )
    }
};