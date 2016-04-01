package models

import models.v1._

sealed abstract class Hand(val cards: List[Card], val order: Int, val name: String) {
  def >(other: Hand): Boolean = this.order > other.order
}

case class StraightFlush(override val cards: List[Card], override val name: String = "Straight Flush") extends Hand(cards, 8, name)
case class FourOfAKind(override val cards: List[Card], override val name: String = "Four Of A Kind") extends Hand(cards, 7, name)
case class FullHouse(override val cards: List[Card], override val name: String = "Full House") extends Hand(cards, 6, name)
case class Flush(override val cards: List[Card], override val name: String = "Flush") extends Hand(cards, 5, name)
case class Straight(override val cards: List[Card], override val name: String = "Straight") extends Hand(cards, 4, name)
case class ThreeOfAKind(override val cards: List[Card], override val name: String = "Three Of A Kind") extends Hand(cards, 3, name)
case class TwoPairs(override val cards: List[Card], override val name: String = "Two Pairs") extends Hand(cards, 2, name)
case class OnePair(override val cards: List[Card], override val name: String = "One Pair") extends Hand(cards, 1, name)
case class HighCard(override val cards: List[Card], override val name: String = "High Card") extends Hand(cards, 0, name)