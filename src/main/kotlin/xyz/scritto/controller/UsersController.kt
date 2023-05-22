package xyz.scritto.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.scritto.model.db.User
import xyz.scritto.service.UsersService

@RestController
@RequestMapping("/user")
class UsersController(private val usersService: UsersService) {

    @GetMapping
    fun listUsers(): ResponseEntity<List<User>> {
        return ResponseEntity.ok()
            .body(usersService.getUsers())
    }
}