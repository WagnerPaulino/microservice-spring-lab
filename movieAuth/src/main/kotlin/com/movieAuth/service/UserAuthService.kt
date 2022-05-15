package com.movieAuth.service

import com.movieAuth.domain.UserAuth
import com.movieAuth.repository.UserAuthRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserAuthService(
        @Autowired private val userAuthRepository: UserAuthRepository,
) {

    fun save(user: UserAuth): UserAuth {
        return userAuthRepository.save(user)
    }

    fun getUser(username: String): UserAuth {
        return userAuthRepository.findByUsername(username)
    }
}
