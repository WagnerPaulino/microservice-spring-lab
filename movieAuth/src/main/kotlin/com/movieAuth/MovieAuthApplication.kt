package com.movieAuth

import com.movieAuth.domain.UserAuth
import com.movieAuth.service.UserAuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication class MovieAuthApplication

fun main(args: Array<String>) {
  runApplication<MovieAuthApplication>(*args)
}

@Component
class InitApp(@Autowired private val userService: UserAuthService) : CommandLineRunner {

  override fun run(vararg args: String?) {
    userService.save(UserAuth(1, "admin", "admin", "1"))
  }
}
