package section3_2

import akka.actor.{ActorSystem, Props}
import akka.routing.RandomGroup
import section3_2.Worker.Work

object RandomRouterGroup extends App {
  val system = ActorSystem("Random-Router")

  system.actorOf(Props[Worker], "w1")
  system.actorOf(Props[Worker], "w2")
  system.actorOf(Props[Worker], "w3")

  val paths = List("/user/w1","/user/w2","/user/w3")

  val routerGroup = system.actorOf(RandomGroup(paths).props(),"random-router-group")

  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()

  system.terminate()
}
