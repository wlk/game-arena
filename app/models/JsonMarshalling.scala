package models

import com.github.tototoshi.play.json.JsonNaming
import ai.x.play.json.Jsonx
import models.v1._
import play.api.libs.json._

trait JsonMarshalling {

  implicit val suitFormat = Jsonx.formatInline[Suit]

  implicit val rankFormat = Jsonx.formatInline[Rank]

  implicit val cardFormat = Json.format[Card]

  implicit val deckFormat = Json.format[Deck]

  implicit val playerWrites = JsonNaming.snakecase(Json.writes[Player])

  implicit val gameStateWrites = JsonNaming.snakecase(Json.writes[GameState])
}
