import MusicController.{Play, Stop}
import MusicPlayer.{StartMusic, StopMusic}
import akka.actor.{Actor, ActorSystem, Props}

object MusicController{
  sealed trait ControllerMsg
  case object Play extends ControllerMsg
  case object Stop extends ControllerMsg
  //Never declare an Actor inside an other Actor -> Breaks pattern
  //Define the method props in the companion that defines props for the actor

  def props = Props[MusicController]
}

class MusicController extends Actor {
  def receive = {
    case Play => println("Music started")
    case Stop => println("Music Stopped")
  }
}

object MusicPlayer{
  sealed trait PlayMsg
  case object StopMusic extends PlayMsg
  case object StartMusic extends PlayMsg
}

class MusicPlayer extends Actor {
  def receive = {
    case StopMusic => println("I don't want to stop the music")
    case StartMusic =>
      val controller = context.actorOf(MusicController.props,"controller")
      controller ! Play
    //This defines a general case
    case _ => println("Unknown Message")
  }
}

object Creation extends App{
  val system = ActorSystem("creation")
  val player = system.actorOf(Props[MusicPlayer],"player")
  // From doc: ! means fire and forget (tell), the ? creates a future for a possible reply (ask)
  player ! StartMusic
}
