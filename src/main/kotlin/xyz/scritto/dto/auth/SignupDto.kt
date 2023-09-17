package xyz.scritto.dto.auth

import xyz.scritto.model.db.UserType
import java.beans.ConstructorProperties

data class SignupDto
@ConstructorProperties("firstName", "username", "email", "userType", "password")
constructor(
    val firstName: String,
    val username: String,
    val email: String,
    val userType: UserType,
    var password: String
) {
    operator fun set(s: String, value: String) {
        this[s] = value
    }
}