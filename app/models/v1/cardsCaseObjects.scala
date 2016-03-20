package models.v1

import scala.util.Random

object Suit {
  val suits = List(Clubs, Spades, Hearts, Diamonds)
}

sealed trait Suit
case object Clubs extends Suit
case object Spades extends Suit
case object Hearts extends Suit
case object Diamonds extends Suit

object Rank {
  // Defines ordering of ranks
  val ranks = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
}

sealed trait Rank
case object Two extends Rank
case object Three extends Rank
case object Four extends Rank
case object Five extends Rank
case object Six extends Rank
case object Seven extends Rank
case object Eight extends Rank
case object Nine extends Rank
case object Ten extends Rank
case object Jack extends Rank
case object Queen extends Rank
case object King extends Rank
case object Ace extends Rank

import Rank._
import Suit._

case class Card(suit: Suit, rank: Rank) {
  def isSameSuit(other: Card): Boolean = this.suit == other.suit

  def >(other: Card): Boolean = ranks.indexOf(this.rank) > ranks.indexOf(other.rank)

  def isPairWith(other: Card): Boolean = this.rank == other.rank

}

case class Deck(cards: List[Card]) { // for now using List here, maybe Set is better (?)
  require(isValid)

  def isEmpty = cards.isEmpty

  def shuffle: Deck = new Deck(Random.shuffle(cards))

  def draw: (Option[Card], Deck) = (cards.headOption, new Deck(cards.tail))

  def isValid = cards.distinct == cards // checking for size is redundant (?)
}

object Deck {
  val fullDeck: Deck = Deck(for (s <- suits; r <- ranks) yield Card(s, r))
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

  deckAfterDrawing.cards.head.suit match { // creates a compilation warning
    case Hearts => "serca"
  }
}