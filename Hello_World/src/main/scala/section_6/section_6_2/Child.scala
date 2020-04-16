package section_6.section_6_2

import akka.actor.{ActorRef, Actor}

class Child(parent: ActorRef) extends Actor {
  def receive = {
    case "ping" => parent ! "pong"
  }
}
