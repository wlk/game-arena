package models

import scala.util.Random

sealed trait Suit
case object Clubs extends Suit
case object Spades extends Suit
case object Hearts extends Suit
case object Diamonds extends Suit

sealed trait Face
case object Two extends Face
case object Three extends Face
case object Four extends Face
case object Five extends Face
case object Six extends Face
case object Seven extends Face
case object Eight extends Face
case object Nine extends Face
case object Ten extends Face
case object Jack extends Face
case object Queen extends Face
case object King extends Face
case object Ace extends Face

case class Card(suit: Suit, face: Face) {
  def isSameSuit(other: Card): Boolean = this.suit == other.suit
  def isSameFace(other: Card): Boolean = this.face == other.face

}

case class Deck(cards: List[Card]){
  def shuffle = new Deck(Random.shuffle(cards))

  def draw = (cards.head, new Deck(cards.tail))
}

object Deck {
  val suits = List(Clubs, Spades, Hearts, Diamonds)
  val faces = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)

  val fullDeck: Deck = Deck(for (s <- suits; f <- faces) yield Card(s, f))
}
