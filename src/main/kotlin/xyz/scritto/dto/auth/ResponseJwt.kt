package xyz.scritto.dto.auth

import java.beans.ConstructorProperties


data class ResponseJwt
@ConstructorProperties("jwt")
constructor(private val jwt: String) : java.io.Serializable {
    fun getJwt(): String {
        return jwt
    }
}
