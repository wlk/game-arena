package models

import models.v1.Card

class HandMatcher(val he: HandExtractor) {

  def matchCards(cards: List[Card]): Hand = {
    he.getStraightFlush(cards)
      .orElse(he.getFourOfAKind(cards))
      .orElse(he.getFullHouse(cards))
      .orElse(he.getFlush(cards))
      .orElse(he.getStraight(cards))
      .orElse(he.getThreeOfAKind(cards))
      .orElse(he.getTwoPairs(cards))
      .orElse(he.getOnePair(cards))
      .getOrElse(HighCard(cards.sortedByRank))
  }

}
