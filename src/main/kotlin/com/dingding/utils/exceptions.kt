package com.dingding.utils

/**
 * Created by sanairika on 2017/02/22.
 */
open class WebError(val code: Int, override val message: String) : Exception(message) {
  fun toResponse(): String = Jackson.mapper.writeValueAsString(ErrorResponse(code, message))
}

class ObjectNotFound(message: String) : WebError(404, message)

class BadRequest(message: String) : WebError(400, message)

class Unauthorized(message: String) : WebError(401, message)

class Forbidden(message: String) : WebError(403, message)

class Gone(message: String) : WebError(410, message)

class ServerIntevalError() : WebError(500, "Server Error")

data class ErrorResponse(val code: Int, val message: String)