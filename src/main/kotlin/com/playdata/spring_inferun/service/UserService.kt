package com.playdata.spring_inferun.service

import com.playdata.spring_inferun.dto.CreateUserDto
import com.playdata.spring_inferun.dto.LoginUserDto
import com.playdata.spring_inferun.dto.PasswordUpdaterDto
import com.playdata.spring_inferun.entity.Role
import com.playdata.spring_inferun.entity.Member
import com.playdata.spring_inferun.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class UserService(
    private val userRepository: UserRepository,
    val encoder: PasswordEncoder,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun create(createUserDto: CreateUserDto) : Member =
        Member(
            username = createUserDto.username,
            email = createUserDto.email,
            password = encoder.encode(createUserDto.password),
            role = Role.USER



        ).let (userRepository::save)




    @Transactional
    fun login(loginUserDto: LoginUserDto): Member {
        val email: String = loginUserDto.email
        val password: String = loginUserDto.password
        val member: Member = userRepository.findByEmail(email)
            ?: throw IllegalArgumentException("존재하지 않는 사용자입니다.")

        if (!encoder.matches(password, member.password)) {
            throw IllegalArgumentException("비밀번호가 일치하지 않습니다.")
        }

      return  member
    }

    @Transactional
    fun pwdUpdate(passwordUpdaterDto: PasswordUpdaterDto): Member{
        val email: String = passwordUpdaterDto.email
        var newPassword: String = passwordUpdaterDto.password
    val member: Member = userRepository.findByEmail(email)
          ?: throw IllegalArgumentException("존재하지않은 사용자입니다")
      if (encoder.matches(newPassword, member.password)) {
          throw IllegalArgumentException("기존비밀번호랑 동일합니다 ")
      }

          member.password= encoder.encode(newPassword)  // 새로운 비밀번호를 인코딩후 기존비밀번호에 삽입한다


        return member

    }





    
}