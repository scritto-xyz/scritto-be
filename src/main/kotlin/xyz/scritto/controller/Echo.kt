package xyz.scritto.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Echo {

    @GetMapping("/echo")
    fun echo(): ResponseEntity<String> {
        return ResponseEntity.ok().body("Hello World!");
    }
}