package section_5.section_5_1

import akka.actor.Actor


class Worker extends Actor{
  import Worker._

  def receive = {
    case msg: Work =>
      println(s"I received work message and my actorref: ${self}")
  }
}

object Worker {
  case class Work(message: String)
}
