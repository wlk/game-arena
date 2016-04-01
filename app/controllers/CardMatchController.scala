package controllers

import javax.inject._

import models.JsonMarshalling
import models.v1.Deck
import play.api.libs.json.Json
import play.api.mvc._

@Singleton
class CardMatchController @Inject() extends Controller with JsonMarshalling {
  def draw5 = Action {
    val cards = Deck.fullDeck.shuffle.cards.take(5)

    Ok(Json.toJson(cards))
  }

}
