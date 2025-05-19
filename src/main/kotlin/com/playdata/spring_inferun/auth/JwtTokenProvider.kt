package com.playdata.spring_inferun.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.Jwts.claims
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date


@Component
class JwtTokenProvider {


    @Value("\${jwt.secret}")
    lateinit var secretKey:String

    @Value("\${jwt.expiration}")
    var  expiration : Int =0
    //lateinit
    @Value("\${jwt.secretKeyRt}")
    lateinit var  secretKeyRt:String

    @Value("\${jwt.expirationRt}")
     var  expirationRt:Int?= null

   fun createToken ( email: String , role: String):String {

       val claims: Claims = Jwts.claims().setSubject(email)
       claims["role"] = role
       val now = Date()

       return Jwts.builder()
           .setClaims(claims)
           .setIssuedAt(now)
           .setExpiration(Date(now.time + expiration * 60 * 1000))
           .signWith(SignatureAlgorithm.HS256, this.secretKey)
           .compact()



   }

}