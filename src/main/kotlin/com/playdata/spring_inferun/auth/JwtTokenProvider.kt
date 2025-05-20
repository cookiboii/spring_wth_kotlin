package com.playdata.spring_inferun.auth

import com.playdata.spring_inferun.entity.Role
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Base64
import java.util.Date
import javax.crypto.spec.SecretKeySpec

@Component
class JwtTokenProvider {

    @Value("\${jwt.secret}")
    lateinit var secretKeyString: String

    @Value("\${jwt.expiration}")
    var expiration: Int = 0  // 분 단위

    @Value("\${jwt.secretKeyRt}")
    lateinit var secretKeyRtString: String

    @Value("\${jwt.expirationRt}")
    var expirationRt: Int = 0  // 분 단위, nullable이면 기본값 0 처리 권장

    private lateinit var secretKey: Key
    private lateinit var secretKeyRt: Key

    @PostConstruct
    fun init() {
        val keyBytes = Base64.getDecoder().decode(secretKeyString)
        secretKey = SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.jcaName)

        val keyRtBytes = Base64.getDecoder().decode(secretKeyRtString)
        secretKeyRt = SecretKeySpec(keyRtBytes, SignatureAlgorithm.HS256.jcaName)
    }

    fun createToken(email: String, role: String): String {
        val now = Date()
        val expiryDate = Date(now.time + expiration * 60 * 1000L)

        val claims = Jwts.claims().apply {
            subject = email
            this["role"] = role
        }

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun createRefreshToken(email: String, role: String): String {
        val now = Date()
        val expiryDate = Date(now.time + expirationRt * 60 * 1000L)

        val claims = Jwts.claims().apply {
            subject = email
            this["role"] = role
        }

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKeyRt, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateAndGetTokenUserInfo(token: String): TokenUserInfo {
        val claims: Claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body

        val email = claims.subject
        val roleStr = claims["role", String::class.java] ?: throw IllegalArgumentException("role required")
        val role = Role.valueOf(roleStr)

        return TokenUserInfo(email = email, role = role)
    }
}


