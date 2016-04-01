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

  implicit val straightFlushFormat = Json.format[StraightFlush]
  implicit val fourOfAKindFormat = Json.format[FourOfAKind]
  implicit val fullHouseFormat = Json.format[FullHouse]
  implicit val flushFormat = Json.format[Flush]
  implicit val straightFormat = Json.format[Straight]
  implicit val threeOfAKindFormat = Json.format[ThreeOfAKind]
  implicit val twoPairsFormat = Json.format[TwoPairs]
  implicit val onePairFormat = Json.format[OnePair]
  implicit val highCardFormat = Json.format[HighCard]

  implicit val handWrites = new Writes[Hand] {
    def writes(h: Hand) = h match {
      case h: StraightFlush => straightFlushFormat.writes(h)
      case h: FourOfAKind   => fourOfAKindFormat.writes(h)
      case h: FullHouse     => fullHouseFormat.writes(h)
      case h: Flush         => flushFormat.writes(h)
      case h: Straight      => straightFormat.writes(h)
      case h: ThreeOfAKind  => threeOfAKindFormat.writes(h)
      case h: TwoPairs      => twoPairsFormat.writes(h)
      case h: OnePair       => onePairFormat.writes(h)
      case h: HighCard      => highCardFormat.writes(h)
    }
  }

  implicit val playerWrites = JsonNaming.snakecase(Json.writes[Player])

  implicit val gameStateWrites = JsonNaming.snakecase(Json.writes[GameState])
}
