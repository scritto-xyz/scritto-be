package xyz.scritto.repository

import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.insertAndGenerateKey
import org.ktorm.entity.find
import org.ktorm.entity.toList
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import xyz.scritto.dto.auth.SignupDto
import xyz.scritto.model.db.User
import xyz.scritto.model.db.Users
import xyz.scritto.model.db.users

@Repository
class UsersRepository(private val database: Database) {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    fun listUsers(): List<User> {
        return database.users.toList()
    }

    fun getUserByEmail(email: String): User? {
        return database.users.find { it.email eq email }
    }

    fun createUser(signupDto: SignupDto): User? {

        val insertedId = database
            .insertAndGenerateKey(Users) {
                set(it.first_name, signupDto.firstName)
                set(it.username, signupDto.username)
                set(it.email, signupDto.email)
                set(it.user_type, signupDto.userType)
                set(it.password, signupDto.password)
            }
        val id: Int = insertedId.toString()
            .toInt()
        logger.info("Created new user, ID: $id")
        return database.users.find { it.id eq id }
    }
}