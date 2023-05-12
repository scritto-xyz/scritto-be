package xyz.scritto.config

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Client {

    @Bean
    fun dotenv(): Dotenv? {
        return Dotenv.configure()
            .directory("/")
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load()
    }
}