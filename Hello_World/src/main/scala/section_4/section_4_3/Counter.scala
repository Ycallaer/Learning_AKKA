package section_4.section_4_3

import akka.actor.Actor.Receive
import akka.actor.ActorLogging
import akka.persistence._

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
      takeSnapshot
    case Evt(Decrement(count)) =>
      state = State(count = state.count - count)
      takeSnapshot
  }

  //Persistent recive on recovery mode

  val receiveRecover: Receive = {
    case evt: Evt =>
      println(s"counter receive ${evt} on recovering mode")
      updateState(evt)
    case SnapshotOffer(_, snapshot:State ) =>
      println(s"Counter receive snapshot with data: ${snapshot} on recovering mode")
      state = snapshot
    case RecoveryCompleted =>
      println(s"Recovery complete and now i'll switch to receiving mode")
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

    case SaveSnapshotSuccess(metadata) =>
      println(s"Save snapshot succeeded.")
    case SaveSnapshotFailure(metadata,reason) =>
      println(s"save snapshot failure and failure is ${reason}")
  }

  def takeSnapshot = {
    if (state.count % 5 == 0) {
      saveSnapshot(state)
    }
  }

  //This method will disable recovery
  //override def recovery: Recovery = Recovery.none
}
