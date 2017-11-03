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
    val input = decode[APIInput](inputString).toOption.map(x => x.value.toString())

    val response = APIResponse(200,  Map("Content-Type" -> "application/json"), input.getOrElse("Invalid input"))

    //no spaces converts json to a string
    out.write(response.asJson.noSpaces.getBytes(UTF_8))

  }

  def isPrime(number: Int): Boolean = {
    return false
  }
}