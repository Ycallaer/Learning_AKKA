import java.util.concurrent.TimeUnit

import Checker.{BlackUser, CheckUser, WhiteUser}
import Recorder.NewUser
import Storage.AddUser
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.util.Timeout
import akka.pattern.ask

case class User(username : String, email: String)
/*
* Use case is that we have a request for a new user. Through a checker we check if the user is white or blacklisted.
* If blacklisted we do nothing, if whitelisted we add the user to the storage
* */

object Recorder{
  sealed trait RecorderMsg
  case class NewUser(user: User) extends RecorderMsg
}

object Checker {
  sealed trait CheckerMsg

  case class CheckUser(user: User) extends CheckerMsg

  sealed trait CheckerResponse
  case class BlackUser(user:User) extends CheckerMsg
  case class WhiteUser(user:User) extends CheckerMsg
}

object Storage {
  sealed trait StorageMsg
  case class AddUser(user: User) extends StorageMsg
}

class Storage extends Actor {
  var users = List.empty[User]

  def receive = {
    case AddUser(user) => println(s"Storage: $user added")
      //:: Commpn method or object
      users = user :: users
  }
}


class Checker extends Actor{
  val blackList= List(User("Adam","adam@gmail.com"))

  def receive = {
    case CheckUser(user) if blackList.contains(user) =>
      println(s"Checker: $user in the blacklist")
      sender() ! BlackUser(user)
    case CheckUser(user) =>
      println(s"Checker: $user not in the blacklist")
      sender() ! WhiteUser(user)
  }
}

class Recorder(checker: ActorRef, storage: ActorRef) extends Actor {
  import scala.concurrent.ExecutionContext.Implicits.global

  implicit val timeout = Timeout(5, TimeUnit.SECONDS)
  def receive = {
    case NewUser(user) =>
      // To use the ask you need to import akka.pattern.ask
      (checker ? CheckUser(user)).map  {
        case WhiteUser(user) => storage ! AddUser(user)
        case BlackUser(user) => println(s"Recorder: $user in the blcklist")
      }
  }
}

object TalkToActor extends App{
  val system = ActorSystem("talk-to-actor")
  val checker = system.actorOf(Props[Checker],"checker")
  val storage = system.actorOf(Props[Storage], "storage")
  //First we initiliaze the class of recorder, then we pass all the variables needed in the constructor
  val recorder = system.actorOf(Props(classOf[Recorder],checker,storage), "recorder")

  recorder ! Recorder.NewUser(User("Jon","jon@packt.com"))

  Thread.sleep(100)

  system.terminate()
}

