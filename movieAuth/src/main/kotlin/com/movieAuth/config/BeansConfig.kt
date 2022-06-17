package com.movieAuth.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class BeansConfig {

    @Bean fun passwordEncoder() = BCryptPasswordEncoder()

}