package com.playdata.spring_inferun.repository

import com.playdata.spring_inferun.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Member, Long> {

      //코틀린에서 상속 : 이다

     fun findByEmail(email: String): Member?
}