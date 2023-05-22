package xyz.scritto.model.auth

import java.io.Serializable

data class JwtResponse(val jwtToken: String) : Serializable {
    companion object {
        private const val serialVersionUID = -8091879091924046844L
    }
}