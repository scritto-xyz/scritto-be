package xyz.scritto

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ScrittoApplication

fun main(args: Array<String>) {
    runApplication<ScrittoApplication>(*args)
}
