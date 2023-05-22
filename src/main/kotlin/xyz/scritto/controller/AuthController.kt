package xyz.scritto.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.scritto.model.db.User
import xyz.scritto.service.UsersService

@RestController
@RequestMapping("/auth")
class AuthController(
    private val usersService: UsersService
) {

//    @PostMapping("/login")
//    fun login(@RequestBody authenticationRequest: LoginDto): ResponseEntity<*> {
//        authenticate(authenticationRequest.email, authenticationRequest.password)
//        val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.email)
//        val token = jwtTokenUtil.generateToken(userDetails.username)
//        return ResponseEntity.ok(JwtResponse(token))
//    }
//
//    private fun authenticate(email: String, password: String) {
//        try {
//            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(email, password))
//        } catch (e: DisabledException) {
//            throw Exception("USER_DISABLED", e)
//        } catch (e: BadCredentialsException) {
//            throw Exception("INVALID_CREDENTIALS", e)
//        }
//    }

    @PostMapping("/register")
    fun register(@RequestBody newUser: User): ResponseEntity<*> {
        if (!usersService.validateEmail(newUser.email)) {
            return ResponseEntity.badRequest()
                .body("Invalid email")
        }

        val user = usersService.getUserByUsername(newUser.username)
        if (user != null) {
            return ResponseEntity.badRequest()
                .body("User already exists")
        }

        val createdUser = usersService.createUser(newUser)
        return ResponseEntity.ok(createdUser)
    }
}