package xyz.scritto.controller

import org.ktorm.database.Database
import org.ktorm.entity.toList
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.scritto.model.User
import xyz.scritto.model.users

@RestController
@RequestMapping("/user")
class UserController(val database: Database) {

    @GetMapping
    fun listUsers():  ResponseEntity<List<User>> {
        return ResponseEntity.ok().body(database.users.toList())
    }
}