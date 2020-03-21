package section_4.section_4_4_4_5

package section_4.section_4_5

import akka.NotUsed
import akka.actor.{ActorSystem, Props}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import akka.persistence.query.{EventEnvelope, PersistenceQuery}
import akka.persistence.query.journal.leveldb.scaladsl.LeveldbReadJournal

object Reporter extends App{
  import Account._

  val system = ActorSystem("persistent-query")
  implicit val mat = ActorMaterializer()(system)
  val queries = PersistenceQuery(system).readJournalFor[LeveldbReadJournal](
    LeveldbReadJournal.Identifier
  )

  val evts: Source[EventEnvelope,NotUsed] = queries.eventsByPersistenceId("account",0L,Long.MaxValue)

  evts.runForeach{ evt => println(s"Event ${evt}")}

  Thread.sleep(1000)
  system.terminate()
}

