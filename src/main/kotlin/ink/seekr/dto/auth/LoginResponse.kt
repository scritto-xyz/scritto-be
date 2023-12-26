package ink.seekr.dto.auth

import ink.seekr.model.db.User
import java.beans.ConstructorProperties

data class LoginResponse
@ConstructorProperties("jwt", "user")
constructor(val jwt: String, val user: User)
