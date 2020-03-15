package section3_2
import scala.util
import akka.actor.{Actor, ActorRef, Props, ActorSystem}
import Worker._

//This is called a pool router
class Router extends Actor{
  var routees: List[ActorRef]= _

  override def preStart(): Unit = {
    routees = List.fill(5){
      context.actorOf(Props[Worker])
    }
  }

  def receive(): Receive = {
    case msg: Work =>
      println("I am a router and I received a message ...")
      routees(util.Random.nextInt(routees.size)) forward(msg)
  }

}

class RouterGroup(routees: List[String]) extends Actor{
  def receive = {
  case msg: Work =>
      println(s"I am a Router group and I receive Work Message...")
      context.actorSelection(routees(util.Random.nextInt(routees.size))) forward(msg)
}
}