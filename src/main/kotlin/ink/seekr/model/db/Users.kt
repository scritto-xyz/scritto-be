package ink.seekr.model.db

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.enum
import org.ktorm.schema.int
import org.ktorm.schema.jdbcTimestamp
import org.ktorm.schema.varchar
import java.sql.Timestamp


enum class UserType {
    CLIENT, ARTIST
}

object Users : Table<User>("users") {
    val id = int("id").primaryKey()
        .bindTo { it.id }
    val first_name = varchar("first_name").bindTo { it.firstName }
    val last_name = varchar("last_name").bindTo { it.lastName }
    val username = varchar("username").bindTo { it.username }
    val email = varchar("email").bindTo { it.email }
    val country = varchar("country").bindTo { it.country }
    val state = varchar("state").bindTo { it.state }
    val city = varchar("city").bindTo { it.city }
    val user_type = enum<UserType>("user_type").bindTo { it.userType }
    val password = varchar("password").bindTo { it.password }
    val createdAt = jdbcTimestamp("created_ts").bindTo { it.createdTs }
    val updatedAt = jdbcTimestamp("updated_ts").bindTo { it.updatedTs }
}

interface User : Entity<User> {
    companion object : Entity.Factory<User>()

    val id: Int?
    val firstName: String
    val lastName: String?
    val username: String?
    val email: String
    val country: String?
    val state: String?
    val city: String?
    val userType: UserType
    val password: String
    val createdTs: Timestamp?
    val updatedTs: Timestamp?
}

val Database.users get() = this.sequenceOf(Users)
