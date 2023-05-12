package xyz.scritto.controller

import org.ktorm.database.Database
import org.ktorm.entity.toList
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import xyz.scritto.model.User
import xyz.scritto.model.users

@RestController
class Echo(val database: Database) {

    @GetMapping("/echo")
    fun echo(): ResponseEntity<String> {
        return ResponseEntity.ok().body("Hello World!")
    }
}