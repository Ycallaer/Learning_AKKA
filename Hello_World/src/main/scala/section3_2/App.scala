package section3_2

import akka.actor.{ActorSystem, Props}
import section3_2.Worker.Work

object App extends  App {
  val system = ActorSystem("router")
  val router = system.actorOf(Props[Router])
  router ! Work()
  router ! Work()
  router ! Work()

  Thread.sleep(100)
  system.terminate()
}
