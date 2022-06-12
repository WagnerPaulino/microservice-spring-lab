package com.movieAuth.service

import com.movieAuth.domain.UserAuth
import com.movieAuth.repository.UserAuthRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserAuthService(
        @Autowired private val userAuthRepository: UserAuthRepository,
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user =
                userAuthRepository
                        .findByUsername(username)
                        .orElseThrow({ ->
                            throw UsernameNotFoundException("Username not found in the database")
                        })
        return User(user.username, user.password, emptyList())
    }

    fun save(user: UserAuth): UserAuth {
        return userAuthRepository.save(user)
    }

    fun getUser(username: String): UserAuth {
        return userAuthRepository
                .findByUsername(username)
                .orElseThrow({ ->
                    throw ResponseStatusException(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            "User with username $username was not found!"
                    )
                })
    }
}
