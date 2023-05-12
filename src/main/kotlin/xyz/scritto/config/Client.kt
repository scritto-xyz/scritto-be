package xyz.scritto.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.ktorm.database.Database
import org.ktorm.jackson.KtormModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class Client(val dataSource: DataSource) {
    @Bean
    fun provideDatabase(): Database {
        return Database.connect(dataSource)
    }

    @Bean
    fun provideObjectMapper(): ObjectMapper {
        return ObjectMapper().registerModule(KtormModule())
    }
}