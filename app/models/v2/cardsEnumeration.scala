package models.v2

import scala.util.Random

object Suit extends Enumeration {
  val Clubs, Spades, Hearts, Diamonds = Value
}

object Rank extends Enumeration {
  val Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace = Value
}

case class Card(rank: Rank.Value, suit: Suit.Value) {
  def isSameSuit(other: Card): Boolean = this.suit == other.suit

  def >(other: Card): Boolean = this.rank > other.rank

  def isPairWith(other: Card): Boolean = this.rank == other.rank

}

case class Deck(cards: List[Card]) {
  require(isValid)

  def isEmpty = cards.isEmpty

  def shuffle: Deck = new Deck(Random.shuffle(cards))

  def draw: (Option[Card], Deck) = (cards.headOption, new Deck(cards.tail))

  def isValid = cards.distinct == cards
}

object Deck {
  val fullDeck: Deck = Deck((for (r <- Rank.values; s <- Suit.values) yield Card(r, s)).toList)
}