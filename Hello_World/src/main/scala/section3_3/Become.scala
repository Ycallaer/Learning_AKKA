package section3_3

import akka.actor.{Actor, ActorRef, ActorSystem, Props, Stash}
import section3_3.UserStorage.{Connect, Disconnect, Operation}

case class User(username: String, email: String)

object UserStorage{
  trait DBOperation
  object DBOperation{
    case object Create extends DBOperation
    case object Update extends DBOperation
    case object Read extends DBOperation
    case object Delete extends DBOperation
  }

  case object Connect
  case object Disconnect
  case class Operation(dbOperation: DBOperation, user:Option[User])

}

//If create is send before the connection is open nothing will open, we will fix this iwth stash
class UserStorage extends Actor with Stash {
  def receive: Receive = disconnected

  def connected: Actor.Receive = {
    case Disconnect =>
      println("User Storage disconnect from DB")
      context.unbecome()
    case Operation(op,user) =>
      println(s"User storage receive ${op} to do in user: ${user}")
  }

  def disconnected: Actor.Receive = {
    case Connect =>
      println(s"User storage connected to DB")
      unstashAll() //enqueues messages to the actor mailbox
      context.become(connected)
    case _ =>
      stash() //acts current actor to the stash
  }
}


object BecomeHotSwap extends App {
  import UserStorage._
  //Become and Unbecome allows us to replace actor behaviour at runtime

  val system = ActorSystem("Hotswap-Become")
  val userStorage = system.actorOf(Props[UserStorage], "userstorage")

  //sending message before connect only works if you implement the stash function
  userStorage ! Operation(DBOperation.Create, Some(User("Admin","admin@packt.com")))

  userStorage ! Connect
  userStorage ! Disconnect
  Thread.sleep(100)

  system.terminate()
}