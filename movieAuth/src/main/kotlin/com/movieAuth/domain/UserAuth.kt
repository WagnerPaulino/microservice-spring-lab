package com.movieAuth.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class UserAuth(
        @Id @GeneratedValue var id: Long? = null,
        @Column var name: String? = null,
        @Column var username: String? = null,
        @Column var password: String? = null
)
