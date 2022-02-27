package com.project.service

import org.slf4j.LoggerFactory
import slick.driver.MySQLDriver.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.{Failure, Success}
import com.project.model.slickDB.{OfferTable, offerInfo, _}
import com.project.utils.DBUtil.db

import scala.language.postfixOps

object offerService {

  val logger = LoggerFactory.getLogger(getClass.getSimpleName)
  logger.info("offer Service Start")

  def getAllData(): Seq[offerInfo]= {
    val query = slick_table.result
    var res = Await.result(db.run(query), 10 seconds)
    res = res.toList
    res
  }

  def insertData(offer: offerInfo): Unit = {
    println(offer.offer_id,offer.offer_result)
    val addData =DBIO.seq(
      slick_table += offer
    )
    Await.result(db.run(addData), 10 seconds)
  }

}
