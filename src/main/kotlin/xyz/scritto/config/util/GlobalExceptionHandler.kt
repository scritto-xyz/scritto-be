package xyz.scritto.config.util

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

//@ComponentScan
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    companion object {
        data class ExceptionResponse(val message: String)
    }

    @ExceptionHandler(Exception::class)
    fun handleInitValueException(ex: Exception): ResponseEntity<ExceptionResponse> {
        val response = ExceptionResponse("An error occurred")
        return ResponseEntity.internalServerError()
            .body(response)
    }
}