package xyz.scritto.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.scritto.config.auth.UserAuthenticationProvider
import xyz.scritto.dto.auth.ResponseJwt
import xyz.scritto.model.db.User
import xyz.scritto.service.UsersService

@RestController
@RequestMapping("/auth")
class AuthController(
    private val usersService: UsersService,
    private val userAuthenticationProvider: UserAuthenticationProvider
) {

    @PostMapping("/login")
    fun login(@AuthenticationPrincipal user: User): ResponseEntity<ResponseJwt> {
        val responseJwt = ResponseJwt(userAuthenticationProvider.createToken(user.email))
        return ResponseEntity.ok(responseJwt)
    }

    @PostMapping("/register")
    fun register(@RequestBody newUser: User): ResponseEntity<*> {
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
        return ResponseEntity.ok(createdUser)
    }
}