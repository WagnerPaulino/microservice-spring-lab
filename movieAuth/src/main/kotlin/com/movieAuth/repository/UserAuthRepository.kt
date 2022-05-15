package com.movieAuth.repository

import com.movieAuth.domain.UserAuth
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface UserAuthRepository :
        JpaRepository<UserAuth, Long>, JpaSpecificationExecutor<UserAuth> {
    fun findByUsername(username: String): UserAuth
}
