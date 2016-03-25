package models

import com.github.tototoshi.play.json.JsonNaming
import models.v1._
import play.api.libs.json._

trait JsonMarshalling {

  implicit val suitFormat = new Format[Suit] {
    def writes(s: Suit) = JsString(s.toString)

    def reads(json: JsValue) = json match {
      case JsNull => JsError()
      case _      => JsSuccess(Suit(json.as[String]))
    }
  }

  implicit val rankFormat = new Format[Rank] {
    def writes(r: Rank) = JsString(r.toString)

    def reads(json: JsValue) = json match {
      case JsNull => JsError()
      case _      => JsSuccess(Rank(json.as[String]))
    }
  }

  implicit val cardFormat = Json.format[Card]

  implicit val deckFormat = Json.format[Deck]

  implicit val playerWrites = JsonNaming.snakecase(Json.writes[Player])

  implicit val gameStateWrites = JsonNaming.snakecase(Json.writes[GameState])
}
