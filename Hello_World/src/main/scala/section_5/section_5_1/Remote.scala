package section_5.section_5_1

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory


object MembersService extends App {
  val config = ConfigFactory.load.getConfig("MembersService")

  val system = ActorSystem("MembersService",config)

  val worker = system.actorOf(Props[Worker],"remote-worker")
  println(s"Worker actor path is ${worker.path}")
}

object MembersServiceLookup extends App {
  val config2 = ConfigFactory.load.getConfig("MembersServiceLookup")
  val system2 = ActorSystem("MembersServiceLookup", config2)

  val worker = system2.actorSelection("akka://MembersService@127.0.0.1:25521/user/remote-worker")

  worker ! Worker.Work("Hi Remote Actor")
}