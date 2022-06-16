package com.movieAuth

import com.movieAuth.domain.UserAuth
import com.movieAuth.service.UserAuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication class MovieAuthApplication

fun main(args: Array<String>) {
  runApplication<MovieAuthApplication>(*args)
}

@Component
@Configuration
class InitApp(@Autowired private val userService: UserAuthService) : CommandLineRunner {

  override fun run(vararg args: String?) {
    userService.save(UserAuth(1, "admin", "admin", "1"))
  }

  @Bean fun passwordEncoder() = BCryptPasswordEncoder()
}
