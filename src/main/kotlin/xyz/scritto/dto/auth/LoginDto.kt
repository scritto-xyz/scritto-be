package xyz.scritto.dto.auth

import java.io.Serializable

data class LoginDto(val email: String, val password: String) : Serializable {
    companion object {
        private const val serialVersionUID = 5926468583005150707L
    }
}