package ink.seekr.controller

import ink.seekr.config.auth.UserAuthenticationProvider
import ink.seekr.dto.auth.LoginResponse
import ink.seekr.dto.auth.ResponseJwt
import ink.seekr.dto.auth.SignupDto
import ink.seekr.model.db.User
import ink.seekr.service.UsersService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val usersService: UsersService,
    private val userAuthenticationProvider: UserAuthenticationProvider
) {

    @PostMapping("/login")
    fun login(@AuthenticationPrincipal user: User): ResponseEntity<LoginResponse> {
        val responseJwt: ResponseJwt
        try {
            responseJwt = ResponseJwt(userAuthenticationProvider.createToken(user.email))
        } catch (exception: Exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .build()
        }
        val response = LoginResponse(responseJwt.getJwt(), user)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/register")
    fun register(@RequestBody newUser: SignupDto): ResponseEntity<*> {
        if (!usersService.validateEmail(newUser.email)) {
            return ResponseEntity.badRequest()
                .body("Invalid email")
        }

        val user = usersService.getUserByEmail(newUser.email)
        if (user != null) {
            return ResponseEntity.badRequest()
                .body("User already exists")
        }

        val createdUser = usersService.createUser(newUser)
            ?: return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error creating user")

        return ResponseEntity.ok(createdUser)
    }
}