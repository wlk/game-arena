package controllers

import javax.inject._

import models.JsonMarshalling
import models.v1.Deck
import play.api.libs.json.Json
import play.api.mvc._


@Singleton
class DeckController @Inject() extends Controller with JsonMarshalling {

  def shuffled = Action {
    val deck = Deck.fullDeck.shuffle
    Ok(Json.toJson(deck))
  }

}
