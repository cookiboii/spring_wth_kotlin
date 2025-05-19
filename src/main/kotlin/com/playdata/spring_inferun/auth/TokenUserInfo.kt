package com.playdata.spring_inferun.auth

import com.playdata.spring_inferun.entity.Role

data class TokenUserInfo(val email: String, val role: Role)
