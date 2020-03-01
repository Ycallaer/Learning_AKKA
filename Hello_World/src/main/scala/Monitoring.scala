import SupervisionMonitor.system
import akka.actor.{Actor, ActorRef, ActorSystem, Props, Terminated}

class Ares(athena: ActorRef) extends Actor{

  //The watch method will make sure it monitors the other object
  override def preStart(): Unit = {
    context.watch(athena)
  }

  override def postStop(): Unit = {
    println("Ares postStop...")
  }

  def receive = {
    case Terminated => context.stop(self)
  }
}

class Athena extends Actor {
  def receive = {
    case msg =>
      println(s"Athena received ${msg}")
      context.stop(self)
  }
}

object Monitoring extends App {
  val system = ActorSystem("supervisor")
  val athena =system.actorOf(Props[Athena], "athena")
  val ares = system.actorOf(Props(new Ares(athena)), "ares")
  athena ! "Hi"

  Thread.sleep(1000)
  println()
  system.terminate()
}