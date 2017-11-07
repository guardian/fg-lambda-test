package com.gu.microserviceWorkshop

import  org.scalatest._

class LambdaSpec extends FlatSpec with Matchers {
  it should "decode a body from API Gateway request as a string" in {
    val apiGatewayRequest =
      """
        |{
        |  "body": "{\"value\" :4}"
        |}
      """.stripMargin
    val response = Lambda.handleAPIGatewayRequest(apiGatewayRequest)
    assert(response.body == "Value is 4")
  }
}
