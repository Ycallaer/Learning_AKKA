package section_8.section_8_2

import spray.json.DefaultJsonProtocol

case class IpInfo(ip: String)

object JsonProtocol extends DefaultJsonProtocol {
  implicit val format = jsonFormat1(IpInfo.apply)
}
