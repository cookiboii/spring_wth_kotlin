package com.playdata.spring_inferun.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.jetbrains.annotations.NotNull


@Entity
class Member  (

           @Id
           @GeneratedValue(strategy = GenerationType.IDENTITY)
             val id: Long ? =null,


            var username: String ,

           @Column(unique = true )

           var email: String ,
           var password: String ,


           @Enumerated(EnumType.STRING)
            var role: Role? = Role.USER





    ){

    // JPA가 사용할 기본 생성자 (필수)
    protected constructor() : this(
        username = "",
        email = "",
        password = "",
        role = Role.USER
    )

}