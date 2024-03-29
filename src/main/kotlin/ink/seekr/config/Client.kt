package ink.seekr.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.validator.routines.EmailValidator
import org.ktorm.database.Database
import org.ktorm.jackson.KtormModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.sql.DataSource

@Configuration
class Client(private val dataSource: DataSource) {
    @Bean
    fun provideDatabase(): Database {
        return Database.connect(dataSource)
    }

    @Bean
    fun provideObjectMapper(): ObjectMapper {
        return ObjectMapper().registerModule(KtormModule())
    }

    @Bean
    fun provideEmailValidator(): EmailValidator {
        return EmailValidator.getInstance()
    }

    @Bean
    fun provideBCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}