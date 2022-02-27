package com.project.Protocol

import com.project.model.slickDB.{offerInfo,Offer}
import spray.json.DefaultJsonProtocol

object MyJsonProtocol extends DefaultJsonProtocol{

  implicit val offerInfoFormat = jsonFormat7(offerInfo)
  implicit val offerFormat = jsonFormat1(Offer)
}
