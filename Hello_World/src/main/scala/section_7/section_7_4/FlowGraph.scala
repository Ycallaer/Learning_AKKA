package section_7.section_7_4

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, ClosedShape, scaladsl}
import akka.stream.scaladsl._

import scala.collection.immutable
import scala.concurrent.{Await, Future}
import scala.util.Random

object GraphFlow extends App {

  implicit val actorSystem = ActorSystem("SimpleStream")
  implicit val actorMaterializer = ActorMaterializer()

  val g = RunnableGraph.fromGraph(GraphDSL.create() { implicit builder: GraphDSL.Builder[NotUsed] =>
    import GraphDSL.Implicits._
    val in = Source(1 to 10)
    val out = Sink.ignore

    val bcast = builder.add(Broadcast[Int](2)) //broadcast to 2 outputs
    val merge = builder.add(Merge[Int](2))

    val f1, f2, f3, f4 = Flow[Int].map(_ + 10)

    in ~> f1 ~> bcast ~> f2 ~> merge ~> f3 ~> out //This is how the graph will execute
    bcast ~> f4 ~> merge
    ClosedShape
  })
  g.run()


  actorSystem.terminate()
}

