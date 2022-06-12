package com.movieAuth.rest

import com.movieAuth.domain.UserAuth
import com.movieAuth.repository.UserAuthRepository
import com.movieAuth.service.UserAuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/users")
class UserAuthRest(
        @Autowired private val userAuthService: UserAuthService,
        @Autowired private val userAuthRepository: UserAuthRepository
) {

    @GetMapping("/{id}")
    fun findUserById(@PathVariable id: Long): UserAuth {
        return userAuthRepository.getById(id)
    }

    @GetMapping("/name/{username}")
    fun getUserByUsername(@PathVariable username: String): UserAuth {
        return userAuthService.getUser(username)
    }

    @PostMapping
    fun insertUser(@RequestBody user: UserAuth): UserAuth {
        return userAuthService.save(user)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: UserAuth): UserAuth {
        userAuthRepository.findById(id).orElseThrow {
            throw ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Movie with id $id was not found!"
            )
        }
        user.id = id
        return userAuthService.save(user)
    }
}
