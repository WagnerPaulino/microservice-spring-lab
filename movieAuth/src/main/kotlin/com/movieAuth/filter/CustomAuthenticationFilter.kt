package com.movieAuth.filter

import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.http.MediaType
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.JWT
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.Date

class CustomAuthenticationFilter(private val authenticationManager: AuthenticationManager) :
        UsernamePasswordAuthenticationFilter() {

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(
            request: HttpServletRequest,
            response: HttpServletResponse
    ): Authentication {
        val username = request.getParameter("username")
        val password = request.getParameter("password")
        val authenticationToken = UsernamePasswordAuthenticationToken(username, password)
        return authenticationManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain,
            authentication: Authentication
    ) {
        val user = authentication.getPrincipal() as User
        val algorithm = Algorithm.HMAC256("secret")
        val accessToken = JWT.create()
                .withSubject(user.username)
                .withExpiresAt(Date(System.currentTimeMillis()+10*60*1000))
                .withIssuer(request.requestURL.toString())
                .sign(algorithm)
        val refreshToken = JWT.create()
                .withSubject(user.username)
                .withExpiresAt(Date(System.currentTimeMillis()+30*60*1000))
                .withIssuer(request.requestURL.toString())
                .sign(algorithm)
        val token = mutableMapOf<String, String>()
        token.put("access_token", accessToken)
        token.put("refresh_token", refreshToken)
        response.setContentType(MediaType.APPLICATION_JSON_VALUE)
        ObjectMapper().writeValue(response.outputStream, token)
    }
}
