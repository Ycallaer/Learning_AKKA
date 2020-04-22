package section_9.section_9_2

import akka.actor.{Actor, ActorSystem, Props}

import scala.concurrent.duration._
import java.util.Date

import section_9.section_9_2.pattern.Throttler.{Rate, SetTarget}
import section_9.section_9_2.pattern.TimerBasedThrottler
import section_9.section_9_2.pattern.TimerBasedThrottler


class Target extends Actor {

  def receive = {
    case msg =>
      println(s"[${new Date().toString}}] I receive msg: $msg")
  }
}


object ThottlerApp extends App{

  val system = ActorSystem("Thottler-Messages")

  val target = system.actorOf(Props[Target], "target")

  val throttler = system.actorOf(Props(new TimerBasedThrottler(new Rate(3,1.second))))

  throttler ! SetTarget(Some(target))

  throttler ! "1"
  throttler ! "2"
  throttler ! "3"
  throttler ! "4"
  throttler ! "5"

  Thread.sleep(5000)
  system.terminate()

}
