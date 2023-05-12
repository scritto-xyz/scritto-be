package xyz.scritto

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication //(exclude =  [ DataSourceAutoConfiguration::class ])
class ScrittoApplication

fun main(args: Array<String>) {
    runApplication<ScrittoApplication>(*args)
}
