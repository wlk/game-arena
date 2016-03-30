package models

import models.v1.Card
import models.v1.Rank._
import models.v1.Suit._

/*
  Responsible for extracting information about what hands can be arranged from a list of cards.
 */
class HandExtractor {

  def areConsecutive(cards: List[Card]): Boolean = !cards.map(card => ranks.indexOf(card.rank)).sorted.reverse.sliding(2).map(e => e.head - e(1)).exists(e => e != 1)

  def areSameSuit(cards: List[Card]): Boolean = cards.map(_.suit).distinct.size == 1

  def isStraightFlush(cards: List[Card]): Boolean = {
    areConsecutive(cards) &&
      areSameSuit(cards) &&
      cards.size == 5
  }

}