package section_6.section_6_2

import akka.actor.{ActorRefFactory, Actor, Props, ActorRef}

class Parent(childMaker: ActorRefFactory => ActorRef) extends Actor {
  val child = childMaker(context)
  var ponged = false

  def receive = {
    case "ping" => child ! "ping"
    case "pong"   => ponged = true
  }
}