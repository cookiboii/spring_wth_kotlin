package com.playdata.spring_inferun.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

import java.time.LocalDateTime


@Entity
class Post(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                  val id : Long ?= null ,
           var  title : String ,
           var content : String,

           var creationDate : LocalDateTime =LocalDateTime.now(),


    ) {
    protected constructor():this (
         title = "",
        content = "",
        creationDate = LocalDateTime.now(),


    )

}