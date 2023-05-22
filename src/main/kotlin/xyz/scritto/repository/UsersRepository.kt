package xyz.scritto.repository

import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.toList
import org.springframework.stereotype.Repository
import xyz.scritto.model.db.User
import xyz.scritto.model.db.users

@Repository
class UsersRepository(private val database: Database) {

    fun listUsers(): List<User> {
        return database.users.toList()
    }

    fun getUserByEmail(email: String): User? {
        return database.users.find { it.email eq email }
    }

    fun createUser(user: User): User {
        val newId = database.users.add(user)
        return database.users.find { it.id eq newId }!!
    }
}