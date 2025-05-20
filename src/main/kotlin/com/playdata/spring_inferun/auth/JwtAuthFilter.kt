package com.playdata.spring_inferun.auth

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@Slf4j
class JwtAuthFilter(JwtTokenProvider: JwtTokenProvider) : OncePerRequestFilter() {

    val JwtTokenProvider: JwtTokenProvider = JwtTokenProvider



    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

    }
}