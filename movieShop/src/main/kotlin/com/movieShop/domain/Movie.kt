package com.movieShop.domain

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column

@Entity
class Movie(@Column(nullable = false) var name: String? = null,
            @Column(nullable = false)var description: String? = null,
            @Id @GeneratedValue var id: Long? = null)