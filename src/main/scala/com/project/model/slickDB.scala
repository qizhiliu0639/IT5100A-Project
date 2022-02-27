package com.project.model
import com.project.model.slickDB.OfferTable
import slick.driver.MySQLDriver.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object slickDB {

  final case class offerInfo(offer_id:Long, user_id:Long, offer_result:String,
                      offer_college_name:String, offer_semester:String,
                      offer_project:String,offer_professional:String)

  final case class Offer(offers: Seq[offerInfo])

  class OfferTable(tag: Tag) extends Table[offerInfo](tag, "offer")
  {
    // define column attribute
    def offer_id = column[Long]("offer_id", O.PrimaryKey, O.AutoInc)
    def user_id = column[Long]("user_id")
    def offer_result = column[String]("offer_result")
    def offer_college_name = column[String]("offer_college_name")
    def offer_semester = column[String]("offer_semester")
    def offer_project = column[String]("offer_project")
    def offer_professional = column[String]("offer_professional")
    def * = (
      offer_id,user_id, offer_result,offer_college_name,
      offer_semester,offer_project,offer_professional
    ) <> (offerInfo.tupled, offerInfo.unapply)
  }
  var slick_table = TableQuery[OfferTable]

}