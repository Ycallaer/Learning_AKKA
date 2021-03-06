package section_4.section_4_2

import akka.actor.Actor.Receive
import akka.actor.ActorLogging
import akka.persistence.{PersistentActor, SnapshotOffer}

//Persistent Actors takes command and events as params

object Counter {
  sealed trait Operation {
    val count : Int
  }

  case class Increment(override val count: Int) extends Operation
  case class Decrement(override val count: Int) extends Operation

  case class Cmd(op:Operation)
  case class Evt(op:Operation)

  case class State(count: Int)
}

class Counter extends PersistentActor with ActorLogging{
  import Counter._

  println("Starting ..........")

  //Persistent identifier -> Needs to be unique

  override def persistenceId = "counter-example"

  var state: State= State(count=0)

  def updateState(evt: Evt): Unit = evt  match {
    case Evt(Increment(count)) =>
      state = State(count = state.count + count)
    case Evt(Decrement(count)) =>
      state = State(count = state.count - count)
  }

  //Persistent recive on recovery mode

  val receiveRecover: Receive = {
    case evt: Evt =>
      println(s"counter receive ${evt} on recovering mode")
      updateState(evt)
    case SnapshotOffer(_, snapshot:State ) =>
      println(s"Counter receive snapshot with data: ${snapshot} on recovering mode")
      state = snapshot
  }


  //Persistent receive on normal mode
  val receiveCommand: Receive = {
    case cmd @ Cmd(op) =>
      println(s"Counter receive ${cmd}")
      persist(Evt(op)) {
        evt =>
          updateState(evt)
      }
    case "print" =>
      println(s"The current state of counter is ${state}")
  }
}
