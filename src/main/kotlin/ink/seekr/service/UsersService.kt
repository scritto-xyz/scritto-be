package ink.seekr.service

import ink.seekr.dto.auth.LoginDto
import ink.seekr.dto.auth.SignupDto
import ink.seekr.model.db.User
import ink.seekr.repository.UsersRepository
import org.apache.commons.validator.routines.EmailValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UsersService(
    private val usersRepository: UsersRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val emailValidator: EmailValidator,
) {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    fun getUsers(): List<User> {
        return usersRepository.listUsers()
    }

    fun getArtists(): List<User> {
        return usersRepository.listArtists()
    }

    fun getUserByEmail(username: String): User? {
        return usersRepository.getUserByEmail(username)
    }

    fun createUser(user: SignupDto): User? {
        val encodedPassword = passwordEncoder.encode(user.password)
        user.password = encodedPassword
        return try {
            usersRepository.createUser(user)
        } catch (err: Exception) {
            logger.error("Error occurred with creating user, email: ${user.email}")
            null
        }
    }

    fun validateEmail(email: String): Boolean {
        return emailValidator.isValid(email)
    }

    fun authenticate(credentials: LoginDto): User {
        val dbUser = usersRepository.getUserByEmail(credentials.email)
        if (dbUser == null || !passwordEncoder.matches(credentials.password, dbUser.password)) {
            throw Exception("Invalid credentials")
        }
        return dbUser
    }
}