package com.movieAuth

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.context.annotation.Bean
import com.movieAuth.service.UserAuthService
import com.movieAuth.domain.UserAuth

@SpringBootApplication class MovieAuthApplication

fun main(args: Array<String>) {
    runApplication<MovieAuthApplication>(*args)
}

@Component
class InitApp(@Autowired private val userService: UserAuthService) : CommandLineRunner {
    override fun run(vararg args: String?) {
		userService.save(UserAuth(1,"John","john","1"))
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
      return BCryptPasswordEncoder()
    }
}
