package xyz.scritto.service

import org.apache.commons.validator.routines.EmailValidator
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import xyz.scritto.model.db.User
import xyz.scritto.repository.UsersRepository

@Service
class UsersService(
    private val usersRepository: UsersRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val emailValidator: EmailValidator,
) {

    fun getUsers(): List<User> {
        return usersRepository.listUsers()
    }

    fun getUserByUsername(username: String): User? {
        return usersRepository.getUserByEmail(username)
    }

    fun createUser(user: User): User {
        val encodedPassword = passwordEncoder.encode(user.password)
        user["password"] = encodedPassword
        return usersRepository.createUser(user)
    }

    fun validateEmail(email: String): Boolean {
        return emailValidator.isValid(email)
    }
}