package xyz.scritto.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class Echo {

    @GetMapping("/echo")
    fun  echo(): ResponseEntity<String> {
        return ResponseEntity.ok().body("Hello World!");
    }
}