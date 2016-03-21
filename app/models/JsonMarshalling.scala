package models

import models.v1._
import play.api.libs.json._

trait JsonMarshalling {

  implicit val suitWrites = new Writes[Suit] {
    def writes(s: Suit) = Json.obj(
      "suit" -> s.toString)
  }

  implicit val rankFormat = new Writes[Rank] {
    def writes(r: Rank) = Json.obj(
      "rank" -> r.toString)
  }

  implicit val cardFormat = Json.writes[Card]

  implicit val deckFormat = Json.writes[Deck]
}
