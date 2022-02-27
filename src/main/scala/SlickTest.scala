
import akka.Done
import org.slf4j.LoggerFactory
import slick.driver.MySQLDriver.api._
import com.project.model.slickDB.OfferTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.util.{Failure, Success}
import com.project.model.slickDB._
import com.project.service.offerService
import com.project.utils.DBUtil.db
import spray.json.enrichAny


object SlickTest extends App{

  val logger = LoggerFactory.getLogger(getClass.getSimpleName)
  logger.info("slick test Start")

//  val db = Database.forURL(
//    url = "jdbc:mysql://localhost:3306/offerDB?useUnicode=true&characterEncoding=UTF-8&useSSL=false",
//    driver = "com.mysql.cj.jdbc.Driver",
//    user = "root",
//    password = "2220718-lzqj")



//  val res1 = db.run(query_action).andThen {
//    case Success(_) => println("query success")
//    case Failure(e) => println("query failed ", e.getMessage)
//  }


//  db.run(slick_table.result).map {
//    result => println(result)
//  }

//  Await.result(res1, 10 seconds)
//
//  println(res1.getClass)
//
//  val q1 = slick_table.filter(_.offer_college_name === "%Cambridge%")
//  val res2 = Await.result(db.run(q1.result),10 seconds)
//
//  println(res2.getClass)


  val res2 = offerService.getAllData()

  println(res2.last)

  offerService.insertData(offerInfo(60000,60000,"Offer","Home University","2015 Fall","None","Chemistry"))

  val res1 = offerService.getAllData()

  println(res1.last)

  println(res1.find(o => o.user_id == 60000))


}
