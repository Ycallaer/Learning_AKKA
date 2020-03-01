import java.util.concurrent.TimeUnit

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorRef, ActorSystem, OneForOneStrategy, Props}

import scala.concurrent.duration.Duration

class Aphrodite extends Actor{
  import Aphrodite._

  override def preStart(): Unit = {
    println("Aphrodite prestart hook")
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("Aphrodite PreRestart hook..")
    super.preRestart(reason,message)
  }

  override def postStop(): Unit = {
    println("Aphrodite postStop...")
  }

  def receive = {
    case "Resume" => throw ResumeException
    case "Stop" => throw StopException
    case "Restart" => throw RestartException
    case _ => throw new Exception
  }
}

object Aphrodite {
  case object ResumeException extends Exception
  case object StopException extends Exception
  case object RestartException extends Exception
}

class Hera extends Actor {
  import Aphrodite._
  var childRef: ActorRef = _
  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10,withinTimeRange = Duration(1, TimeUnit.SECONDS)){
      case ResumeException => Resume
      case RestartException => Restart
      case StopException => Stop
      case _: Exception => Escalate
    }

  override def preStart(): Unit = {
    childRef = context.actorOf(Props[Aphrodite],"Aphrodite")
    Thread.sleep(100)
  }

  def receive = {
    case msg =>
      println(s"Hera received ${msg}")
      childRef ! msg
      Thread.sleep(100)
  }
}

object SupervisionMonitor extends App {
  val system = ActorSystem("supervision")
  val hera = system.actorOf(Props[Hera], "hera")
  hera ! "Resume"
  Thread.sleep(1000)
  println()
  system.terminate()
}