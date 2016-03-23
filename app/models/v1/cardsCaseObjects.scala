package models.v1

import scala.util.Random

object Suit {
  val suits = List(Clubs, Spades, Hearts, Diamonds)
  private val fromStringMap: Map[String, Suit] = Map(Clubs.toString -> Clubs, Spades.toString -> Spades, Hearts.toString -> Hearts, Diamonds.toString -> Diamonds)
  def apply(key: String) = fromStringMap(key)
}

sealed abstract class Suit

case object Clubs extends Suit {
  override def toString = "clubs"
}

case object Spades extends Suit {
  override def toString = "spades"
}

case object Hearts extends Suit {
  override def toString = "hearts"
}

case object Diamonds extends Suit {
  override def toString = "diamonds"
}

object Rank {
  // Defines ordering of ranks
  val ranks = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)

  private val fromStringMap: Map[String, Rank] = Map(Two.toString -> Two, Three.toString -> Three, Four.toString -> Four, Five.toString -> Five, Six.toString -> Six, Seven.toString -> Seven, Eight.toString -> Eight, Nine.toString -> Nine, Ten.toString -> Ten, Jack.toString -> Jack, Queen.toString -> Queen, King.toString -> King, Ace.toString -> Ace)

  def apply(key: String) = fromStringMap(key)
}

sealed abstract class Rank

case object Two extends Rank {
  override def toString = "2"
}

case object Three extends Rank{
  override def toString = "3"
}

case object Four extends Rank{
  override def toString = "4"
}

case object Five extends Rank{
  override def toString = "5"
}

case object Six extends Rank{
  override def toString = "6"
}

case object Seven extends Rank{
  override def toString = "7"
}

case object Eight extends Rank{
  override def toString = "8"
}

case object Nine extends Rank{
  override def toString = "9"
}

case object Ten extends Rank{
  override def toString = "10"
}

case object Jack extends Rank{
  override def toString = "J"
}

case object Queen extends Rank{
  override def toString = "Q"
}

case object King extends Rank{
  override def toString = "K"
}

case object Ace extends Rank{
  override def toString = "A"
}

import models.v1.Rank._
import models.v1.Suit._

case class Card(rank: Rank, suit: Suit) {
  def isSameSuit(other: Card): Boolean = this.suit == other.suit

  def >(other: Card): Boolean = ranks.indexOf(this.rank) > ranks.indexOf(other.rank)

  def isPairWith(other: Card): Boolean = this.rank == other.rank

}

case class Deck(cards: List[Card]) {
  // for now using List here, maybe Set is better (?)
  require(isValid)

  def isEmpty = cards.isEmpty

  def shuffle: Deck = new Deck(Random.shuffle(cards))

  def draw: (Option[Card], Deck) = (cards.headOption, new Deck(cards.tail))

  def isValid = cards.distinct == cards // checking for size is redundant (?)
}

object Deck {
  val fullDeck: Deck = Deck(for (r <- ranks; s <- suits) yield Card(r, s))
}

object Playground extends App {

  val aceOfSpades = Card(Ace, Spades)
  val tenOfHearts = Card(Ten, Hearts)
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

  /*  deckAfterDrawing.cards.head.suit match { // creates a compilation warning
      case Hearts => "serca"
    }*/
}