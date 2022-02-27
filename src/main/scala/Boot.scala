import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.Done
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import com.project.Protocol.MyJsonProtocol._
import com.project.model.slickDB.offerInfo
import com.project.service.offerService
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

import scala.io.StdIn

import scala.concurrent.Future


object Boot {

  // needed to run the route
  implicit val system = ActorSystem(Behaviors.empty, "Offer_Sharing_Platform")
  // needed for the future map/flatmap in the end and future in fetchItem and saveOrder
  implicit val executionContext = system.executionContext

  def getOfferById(offerid:Long): Future[Option[offerInfo]] = Future{
    val res = offerService.getAllData()
    res.find(o => o.offer_id == offerid)
  }

  def getOffer(offerid:Long): Future[Option[offerInfo]] = Future{
    val res = offerService.getAllData()
    res.find(o => o.offer_id == offerid)
  }

  def getOfferByUserId(userid:Long): Future[Option[offerInfo]] = Future{
    val res = offerService.getAllData()
    res.find(o => o.user_id == userid)
  }

  def getAllOffer(): Future[Seq[offerInfo]] = Future{
    val res = offerService.getAllData()
    res
  }

  def AddOneOffer(offer:offerInfo): Future[Done] = {
    offerService.insertData(offer)
    Future {Done}
  }

  def main(args: Array[String]): Unit = {
    val route: Route =
      concat(
        get {
          pathPrefix("offer" / LongNumber) { id =>
            // there might be no item for a given id
            val maybeItem: Future[Option[offerInfo]] = getOfferById(id)

            onSuccess(maybeItem) {
              case Some(item) => complete(item)
              case None       => complete(StatusCodes.NotFound)
            }
          }
        },
        get {
          pathPrefix("all-offer" ) {
            // there might be no item for a given id
            val maybeItem: Future[Seq[offerInfo]] = getAllOffer()
            complete(maybeItem)
          }
        },
        post{
          path("add-one-offer") {
            entity(as[offerInfo]) { offer =>
              val success: Future[Done] = AddOneOffer(offer)
              onSuccess(success) {_ =>
                complete("Successfully add offer")
              }
            }
          }
        },
        get {
          pathPrefix("offerById" / LongNumber) { id =>
            // there might be no item for a given id
            val maybeItem: Future[Option[offerInfo]] = getOfferByUserId(id)

            onSuccess(maybeItem) {
              case Some(item) => complete(item)
              case None       => complete(StatusCodes.NotFound)
            }
          }
        }
      )

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
