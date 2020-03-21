package section_4.section_4_4_4_5

import akka.actor.{ActorSystem, Props}

object PersistentFSM extends App{
  import Account._

  val system = ActorSystem("persistent-fsm-actor")
  val account = system.actorOf(Props[Account])
  account ! Operation(1000,CR)
  account ! Operation(10,DR)

  Thread.sleep(1000)
  system.terminate()
}
