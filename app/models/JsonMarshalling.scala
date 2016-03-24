package models

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

  implicit val playerWrites = new Writes[Player] {
    def writes(p: Player) = Json.obj(
      "id" -> p.id,
      "name" -> p.name,
      "status" -> p.status,
      "version" -> p.version,
      "stack" -> p.stack,
      "bet" -> p.bet,
      "hole_cards" -> p.holeCards
    )
  }

  implicit val gameStateWrites = new Writes[GameState] {
    def writes(g: GameState) = Json.obj(
      "tournament_id" -> g.tournamentId,
      "game_id" -> g.gameId,
      "round" -> g.round,
      "bet_index" -> g.betIndex,
      "small_blind" -> g.smallBlind,
      "current_buy_in" -> g.currentBuyIn,
      "pot" -> g.pot,
      "minimum_raise" -> g.minimumRaise,
      "dealer" -> g.dealer,
      "orbits" -> g.orbits,
      "in_action" -> g.inAction,
      "players" -> g.players, //intellij complains here, but it's correct and project compiles OK
      "community_cards" -> g.communityCards
    )
  }
}
