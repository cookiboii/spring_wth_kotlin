package com.playdata.spring_inferun.controller

import com.playdata.spring_inferun.dto.CommonResDto
import com.playdata.spring_inferun.dto.CreateUserDto
import com.playdata.spring_inferun.dto.LoginUserDto
import com.playdata.spring_inferun.entity.Member
import com.playdata.spring_inferun.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping


import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/users")
class UserController(

    private  val userService: UserService

) {

    @PostMapping("/create")
    fun userCreate(@RequestBody createUserDto: CreateUserDto): ResponseEntity<CommonResDto<Member>> {
        val user  = userService.create(createUserDto)
        return ResponseEntity.ok(
            CommonResDto(
                statusCode = 200,
                message ="created",
                result = user
            )
        )
    }
    @GetMapping("/login")
    fun userLogin(loginUserDto: LoginUserDto): Member {
      val  users = userService.login(loginUserDto)

    }

}