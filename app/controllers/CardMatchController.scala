package controllers

import javax.inject._

import models._
import models.v1.Deck
import play.api.libs.json.Json
import play.api.mvc._

@Singleton
class CardMatchController @Inject() extends Controller with JsonMarshalling {

  def draw5 = Action {
    val (cards, _) = Deck.fullDeck.shuffle.draw5
    val hm = new HandMatcher(new HandExtractor)
    val matchedHand = hm.matchCards(cards)

    Ok(Json.toJson(matchedHand))
  }

}
