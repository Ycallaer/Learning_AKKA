package section3_3


import akka.actor.{Actor, ActorRef, ActorSystem, FSM, Props, Stash}
import section3_3.UserStorageFSM.{DBOperation, Operation, _}

object UserStorageFSM {
  sealed trait State
  case object Connected extends State
  case object Disconnected extends State


  sealed trait Data
  case object EmptyData extends Data

  trait DBOperation
  object DBOperation{
    case object Create extends DBOperation
    case object Update extends DBOperation
    case object Read extends DBOperation
    case object Delete extends DBOperation
  }

  case object Connect
  case object Disconnect
  case class Operation(dbOperation: DBOperation, user:User)

  case class User(username: String, email: String)
}

class UserStorageFSM extends FSM[UserStorageFSM.State,UserStorageFSM.Data] with Stash{
  import UserStorageFSM._
  //1. define the start with

  startWith(Disconnected,EmptyData)

  //2. define states
  when(Disconnected){
    case Event(Connect, _) =>
      println(s"Userstorage connected to DB")
      unstashAll()
      goto(Connected) using(EmptyData)
    case Event(_, _) =>
      stash()
      stay using(EmptyData)
  }

  when(Connected){
    case Event(Disconnect, _) =>
      println(s"Userstorage disconnected from DB")
      goto(Disconnected) using(EmptyData)
    case Event(Operation(op,user), _) =>
      println(s"User storage receive ${op} to do in user: ${user}")
      stay using(EmptyData)
  }

  //3. Initialize
  initialize()
}

object FiniteStateMachine extends App {
  import UserStorageFSM._

  val system = ActorSystem("Hotswap-FSM")
  val userStorage = system.actorOf(Props[UserStorageFSM], "userstorage-fsm")

  userStorage ! Connect

  userStorage ! Operation(DBOperation.Create, (User("Admin","admin@packt.com")))

  userStorage ! Disconnect
  Thread.sleep(100)

  system.terminate()
}
