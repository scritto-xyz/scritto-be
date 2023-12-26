package ink.seekr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [UserDetailsServiceAutoConfiguration::class])
class SeekrApplication

fun main(args: Array<String>) {
    runApplication<SeekrApplication>(*args)
}
