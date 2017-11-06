package com.gu.microserviceWorkshop

import io.circe.syntax._
import io.circe.parser._
import java.io._
import java.nio.charset.StandardCharsets.UTF_8
import scala.io._

object Lambda {
  def handler(in: InputStream, out: OutputStream): Unit = {

    val inputString = Source.fromInputStream(in).mkString("")
    System.out.println(inputString)
    // decode[APIInput](inputString) returns Either
    System.out.println(decode[APIRequest](inputString).left.map(_.toString))
    val body = decode[APIRequest](inputString).toOption.map(x => x.body)
    val request = decode[APIInput](body.getOrElse("")).toOption.map(x => x.value.toString)

    val response = APIResponse(200,  Map("Content-Type" -> "application/json"), request.getOrElse("Invalid input"))

    //no spaces converts json to a string
    out.write(response.asJson.noSpaces.getBytes(UTF_8))

  }

  def isPrime(number: Int): Boolean = {
    return false
  }
}