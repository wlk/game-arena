package models

import models.v1._

/*
 I'm unsure if this is needed now, and what form this might take in the future, for now working with Lists
 */
sealed abstract class Hand(val cards: List[Card]) {
  protected val order: Int // this is my workaround for not being able to order types, but I'm sure there is a way to do this
  def >(other: Hand): Boolean = this.order > other.order
}
case class StraightFlush(override val cards: List[Card]) extends Hand(cards) {
  override protected val order = 8
}
case class FourOfAKind(override val cards: List[Card]) extends Hand(cards) {
  override protected val order = 7
}
case class FullHouse(override val cards: List[Card]) extends Hand(cards) {
  override protected val order = 6
}
case class Flush(override val cards: List[Card]) extends Hand(cards) {
  override protected val order = 5
}
case class Straight(override val cards: List[Card]) extends Hand(cards) {
  override protected val order = 4
}
case class ThreeOfAKind(override val cards: List[Card]) extends Hand(cards) {
  override protected val order = 3
}
case class TwoPairs(override val cards: List[Card]) extends Hand(cards) {
  override protected val order = 2
}
case class OnePair(override val cards: List[Card]) extends Hand(cards) {
  override protected val order = 1
}
case class HighCard(override val cards: List[Card]) extends Hand(cards) {
  override protected val order = 0
}

object Playground extends App {
  val h1 = StraightFlush(List(Card(Jack, Spades), Card(Ten, Spades), Card(Nine, Spades), Card(Eight, Spades), Card(Seven, Spades)))
  val h2 = HighCard(List(Card(Ace, Hearts), Card(Ten, Clubs), Card(Nine, Hearts), Card(Four, Clubs), Card(Ace, Hearts), Card(Three, Diamonds)))

  println(h1)
  println(h2)

  val a = h1 > h2
  println(a)

}

