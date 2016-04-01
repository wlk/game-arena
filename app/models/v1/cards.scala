package models.v1

import scala.util.Random
import models.v1.Rank._
import models.v1.Suit._

object Suit {
  val suits = List(Clubs, Spades, Hearts, Diamonds)
  private val fromStringMap: Map[String, Suit] = suits.map(s => s.toString -> s).toMap
  def apply(key: String) = fromStringMap(key)
}

// should be abstract, workaround for play-json-extensions
sealed class Suit(val value: String) {
  override def toString = value
}

case object Clubs extends Suit("clubs")

case object Spades extends Suit("spades")

case object Hearts extends Suit("hearts")

case object Diamonds extends Suit("diamonds")

object Rank {
  // Defines ordering of ranks
  val ranks = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)

  private val fromStringMap: Map[String, Rank] = ranks.map(r => r.toString -> r).toMap

  def apply(key: String) = fromStringMap(key)
}

// should be abstract, workaround for play-json-extensions
sealed class Rank(val value: String) {
  override def toString = value

  def >(other: Rank): Boolean = Rank.ranks.indexOf(this) > Rank.ranks.indexOf(other)

}

case object Two extends Rank("2")

case object Three extends Rank("3")

case object Four extends Rank("4")

case object Five extends Rank("5")

case object Six extends Rank("6")

case object Seven extends Rank("7")

case object Eight extends Rank("8")

case object Nine extends Rank("9")

case object Ten extends Rank("10")

case object Jack extends Rank("J")

case object Queen extends Rank("Q")

case object King extends Rank("K")

case object Ace extends Rank("A")

case class Card(rank: Rank, suit: Suit) {
  def isSameSuit(other: Card): Boolean = this.suit == other.suit

  def >(other: Card): Boolean = this.rank > other.rank

  def isPairWith(other: Card): Boolean = this.rank == other.rank

}

object Card {

  implicit class CardListSorter(c: List[Card]) {
    def sortedByRank = c.sortWith((c1, c2) => c1 > c2)
  }

}

case class Deck(cards: List[Card]) {
  // for now using List here, maybe Set is better (?)
  require(isValid)

  def isEmpty = cards.isEmpty

  def shuffle: Deck = new Deck(Random.shuffle(cards))

  def draw: (Card, Deck) = (cards.head, new Deck(cards.tail))

  def draw2: (List[Card], Deck) = drawN(2)

  def draw5: (List[Card], Deck) = drawN(5)

  private def drawN(n: Int) = (cards.take(n), new Deck(cards.drop(n)))

  def isValid = cards.distinct == cards // checking for size is redundant (?)
}

object Deck {
  val fullDeck: Deck = Deck(for (r <- ranks; s <- suits) yield Card(r, s))
}
