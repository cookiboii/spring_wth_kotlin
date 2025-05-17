package com.playdata.spring_inferun.dto

data class CommonResDto<T>(val  statusCode: Int , val message: String ,val result:T)
