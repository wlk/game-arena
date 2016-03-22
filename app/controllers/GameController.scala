package controllers

import javax.inject._

import models.v1._
import models.{GameState, JsonMarshalling, Player}
import play.api.libs.json.Json
import play.api.mvc._


@Singleton
class GameController @Inject() extends Controller with JsonMarshalling {

  def gameState = Action {
    val holeCards = List(Card(Ace, Spades), Card(Ten, Spades))
    val communityCards = List(Card(Queen, Spades), Card(Jack, Spades))
    val player = Player(1, "wojtek test", "active", "test version", 3, 32, holeCards)
    val state = GameState("tournamentIdddddd", "gameIdddddd", 1, 10, 23, 33, 33, 12, 1, 7, 1, List(player), communityCards)
    Ok(Json.toJson(state))
  }

}
