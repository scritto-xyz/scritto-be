package ink.seekr.controller

import ink.seekr.model.db.User
import ink.seekr.service.UsersService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UsersController(private val usersService: UsersService) {

    @GetMapping
    fun listUsers(): ResponseEntity<List<User>> {
        return ResponseEntity.ok()
            .body(usersService.getUsers())
    }
}