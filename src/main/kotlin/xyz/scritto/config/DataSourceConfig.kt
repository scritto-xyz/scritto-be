package xyz.scritto.config

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource


@Configuration
class DataSourceConfig(val dotenv: Dotenv) {

    @Bean
    fun dataSource(): DataSource? {
        val dataSourceBuilder: DataSourceBuilder<*> = DataSourceBuilder.create()
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver")
        dataSourceBuilder.url(dotenv["DB_URL"])
        dataSourceBuilder.username(dotenv["DB_USERNAME"])
        dataSourceBuilder.password(dotenv["DB_PASSWORD"])
        return dataSourceBuilder.build()
    }
}