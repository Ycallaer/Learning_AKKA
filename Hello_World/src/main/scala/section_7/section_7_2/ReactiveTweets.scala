package section_7.section_7_2

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, Materializer}
import akka.stream.scaladsl.{Flow, Sink, Source}

import scala.collection.immutable
import twitter4j.Status

object ReactiveTweets extends App {
  implicit val actorSystem = ActorSystem()
  import actorSystem.dispatcher
  implicit val flowMaterializer = Materializer(actorSystem) //ActorMaterializer()

  //val source = Source( TwitterClient.retrieveTweets("#Akka")) //() => TwitterClient.retrieveTweets("#Akka"))
  val source = Source (1 to 6)

  val normalize = Flow[Status].map{ t =>
    Tweet(Author(t.getUser().getName()), t.getText())
  }

  val sink = Sink.foreach[Tweet](println)

  //connecting the flow with the sink -> Issue with flow and flowshape
  //source.via(normalize).runWith(sink).andThen{
  //  case _ =>
  //    actorSystem.terminate()
  //}

}

