package com.gu.microserviceWorkshop

import io.circe.syntax._
import io.circe.parser._
import java.io._
import java.nio.charset.StandardCharsets.UTF_8
import scala.io._

object Lambda {
  def handler(in: InputStream, out: OutputStream): Unit = {

    val inputString = Source.fromInputStream(in).mkString("")
    // decode[APIInput](inputString) returns Either
    val response = handleAPIGatewayRequest(inputString)

    //no spaces converts json to a string
    out.write(response.asJson.noSpaces.getBytes(UTF_8))

  }

  def handleAPIGatewayRequest(in: String): APIResponse = {
    val body = decode[APIRequest](in).toOption.map(x => x.body)
    body match {
      case Some(s) => {
        val value = decode[APIInput](s).toOption.map(x => x.value)
        value match {
          case Some(number) => {
            val result = APIResult(number, isPrime(number))
            APIResponse(200,  Map("Content-Type" -> "application/json"), result.asJson.noSpaces)
          }
          case None => APIResponse(200, Map("Content-Type" -> "application/json"), "Error decoding body")
        }
      }
      case None => {
        APIResponse(200,  Map("Content-Type" -> "application/json"), "Error decoding body")
      }
    }
  }

  def isPrime(number: Int): Boolean = {
    if (number < 2) {
      return false
    } else if (number <= 3) {
      return true
    } else if (number % 2 == 0) {
      return false
    }
    var n = 3
    while (n <= Math.sqrt(number)) {
      if (number % n == 0) {
        return false
      }
      n += 1
    }
    return true
  }
}