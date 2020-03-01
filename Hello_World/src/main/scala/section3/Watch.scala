package section3

import akka.actor.{ActorSystem, Props}

object Watch extends App{
  val system = ActorSystem("watch-actor-selection")
  val counter = system.actorOf(Props[Counter],"counter")
  val watcher = system.actorOf(Props[Watcher], "watcher")

  system.terminate()
}
