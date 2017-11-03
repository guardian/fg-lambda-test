package com.gu.microserviceWorkshop

import io.circe.Decoder
import io.circe.syntax._
import io.circe.generic.extras.semiauto._

case class APIRequest(body: APIInput)
object APIRequest {

  implicit val APIRequestDecoder : Decoder[APIRequest] = deriveDecoder

}



case class APIInput(value: Int)

object APIInput {

  implicit val APIInputDecoder : Decoder[APIInput] = deriveDecoder

}
