package section3_2

import akka.actor.{ActorSystem, Props}
import akka.routing.FromConfig
import section3_2.Worker.Work

object Random extends App {
  val system = ActorSystem("Random-Router")
  val routerPool = system.actorOf(FromConfig.props(Props[Worker]),"random-router-pool")
  routerPool ! Work()
  routerPool ! Work()
  routerPool ! Work()
  routerPool ! Work()

  Thread.sleep(100)
  system.terminate()
}
