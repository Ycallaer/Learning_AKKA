package section_4.section_4_2

import akka.actor.{ActorSystem, Props}

// In application.conf you need to define the configuration for persistance
//of the journal and snapshot-store

object Persistence extends App {
  import Counter._

  val system = ActorSystem("persistence-actors")
  val counter = system.actorOf(Props[Counter])

  counter ! Cmd(Increment(3))
  counter ! Cmd(Increment(5))
  counter ! Cmd(Decrement(3))

  counter ! "print"

  Thread.sleep(1000)

  system.terminate()


}
