package section_8.section_8_4.models

import spray.json.DefaultJsonProtocol

case class Tweet(author: String, body: String)

object TweetProtocol extends DefaultJsonProtocol {
  implicit val TweetFormat = jsonFormat2(Tweet.apply)
}

