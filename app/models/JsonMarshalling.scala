package models

import models.v1._
import play.api.libs.json._

trait JsonMarshalling {

  implicit val suitWrites = new Writes[Suit] {
    def writes(s: Suit) = JsString(s.toString)
  }

  implicit val rankWrites = new Writes[Rank] {
    def writes(r: Rank) = JsString(r.toString)
  }

  implicit val cardWrites = Json.writes[Card]

  implicit val deckWrites = Json.writes[Deck]

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
      "players" -> g.players,
      "community_cards" -> g.communityCards
    )
  }
}
