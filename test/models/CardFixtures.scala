package models

import models.v1._

trait CardFixtures {
  val straightFlushCards = List(Card(Jack, Clubs), Card(Ten, Clubs), Card(Nine, Clubs), Card(Eight, Clubs), Card(Seven, Clubs))
  val fourOfAKindCards = List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Six, Spades), Card(Jack, Hearts))
  val fullHouseCards = List(Card(Jack, Spades), Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Jack, Hearts))
  val flushCards = List(Card(Seven, Clubs), Card(Nine, Clubs), Card(Queen, Clubs), Card(Ten, Clubs), Card(Eight, Clubs))
  val straightCards = List(Card(Queen, Clubs), Card(King, Diamonds), Card(Jack, Spades), Card(Ten, Hearts), Card(Nine, Spades))
  val threeOfAKindCards = List(Card(King, Diamonds), Card(King, Clubs), Card(Ten, Spades), Card(Nine, Hearts), Card(King, Spades))
  val twoPairCards = List(Card(Queen, Hearts), Card(Two, Clubs), Card(Eight, Hearts), Card(Eight, Spades), Card(Queen, Clubs))
  val onePairCards = List(Card(Seven, Spades), Card(Four, Hearts), Card(King, Spades), Card(Seven, Clubs), Card(Three, Spades))
  val highCardCards = List(Card(Seven, Spades), Card(Four, Hearts), Card(King, Spades), Card(Ace, Clubs), Card(Three, Spades))
}
