package xyz.scritto.repository

import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.entity.find
import org.ktorm.entity.toList
import org.springframework.stereotype.Repository
import xyz.scritto.dto.auth.SignupDto
import xyz.scritto.model.db.User
import xyz.scritto.model.db.Users
import xyz.scritto.model.db.users

@Repository
class UsersRepository(private val database: Database) {

    fun listUsers(): List<User> {
        return database.users.toList()
    }

    fun getUserByEmail(email: String): User? {
        return database.users.find { it.email eq email }
    }

    fun createUser(signupDto: SignupDto): User? {

        val insertedId = database
            .insert(Users) {
                set(it.first_name, signupDto.firstName)
                set(it.username, signupDto.username)
                set(it.email, signupDto.email)
                set(it.user_type, signupDto.userType)
                set(it.password, signupDto.password)
            }

        return database.users.find { it.id eq  insertedId }
    }
}