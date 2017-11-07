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
    val body = decode[APIRequest](inputString).toOption.map(x => x.body)
    val response = body match {
      case Some(s) => {
        val value = decode[APIInput](s).toOption.map(x => x.value)
        APIResponse(200,  Map("Content-Type" -> "application/json"), "Value is " + value.toString)
      }
      case None => {
        APIResponse(200,  Map("Content-Type" -> "application/json"), "Error decoding body")
      }
    }

    //no spaces converts json to a string
    out.write(response.asJson.noSpaces.getBytes(UTF_8))

  }

  def isPrime(number: Int): Boolean = {
    return false
  }
}