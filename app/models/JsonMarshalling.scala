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

  implicit val formatStraight = Json.format[Straight]
  implicit val formatTwoPairs = Json.format[TwoPairs]
  implicit val formatThreeOfAKind = Json.format[ThreeOfAKind]
  implicit val formatStraightFlush = Json.format[StraightFlush]
  implicit val formatOnePair = Json.format[OnePair]
  implicit val formatHighCard = Json.format[HighCard]
  implicit val formatFullHouse = Json.format[FullHouse]
  implicit val formatFlush = Json.format[Flush]
  implicit val formatFourOfAKind = Json.format[FourOfAKind]

  implicit val formatHand = new Writes[Hand] {
    def writes(h: Hand) = h match {
      case h: Straight      => formatStraight.writes(h)
      case h: TwoPairs      => formatTwoPairs.writes(h)
      case h: ThreeOfAKind  => formatThreeOfAKind.writes(h)
      case h: StraightFlush => formatStraightFlush.writes(h)
      case h: OnePair       => formatOnePair.writes(h)
      case h: HighCard      => formatHighCard.writes(h)
      case h: FullHouse     => formatFullHouse.writes(h)
      case h: Flush         => formatFlush.writes(h)
      case h: FourOfAKind   => formatFourOfAKind.writes(h)
    }
  }

  implicit val playerWrites = JsonNaming.snakecase(Json.writes[Player])

  implicit val gameStateWrites = JsonNaming.snakecase(Json.writes[GameState])
}
