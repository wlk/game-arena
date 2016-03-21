package models.v2

import scala.util.Random

object Suit extends Enumeration {
  val Clubs, Spades, Hearts, Diamonds = Value
}

object Rank extends Enumeration {
  val Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace = Value
}

case class Card(suit: Suit.Value, rank: Rank.Value) {
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
  val fullDeck: Deck = Deck((for (s <- Suit.values; r <- Rank.values) yield Card(s, r)).toList)
}

object Playground extends App {

  import Rank._
  import Suit._

  val aceOfSpades = Card(Spades, Ace)
  val tenOfHearts = Card(Hearts, Ten)
  aceOfSpades > tenOfHearts

  val deck1 = Deck.fullDeck
  println(deck1)

  val shuffled = deck1.shuffle

  val firstCard = shuffled.cards.head
  val secondCard = shuffled.cards(1)

  firstCard > secondCard

  firstCard.isPairWith(secondCard)

  val (maybeCardFromTheTop, deckAfterDrawing) = shuffled.draw

  val cardFromTheTop = maybeCardFromTheTop.get // just a playground

  deckAfterDrawing.cards(1) > deckAfterDrawing.cards(2)

  cardFromTheTop > deckAfterDrawing.cards(2)

  /*  deckAfterDrawing.cards.head.suit match { // doesn't create compilation warning
      case Hearts => "serca"
    }*/
}