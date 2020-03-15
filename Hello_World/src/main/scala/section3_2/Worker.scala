package section3_2

import akka.actor.Actor

class Worker extends Actor{
  import Worker._

  def receive = {
    case msg: Work => println(s"I received Work message and My actorRef: ${self}")
  }

}

object Worker{
  case class Work()
}
