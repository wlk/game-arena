package models

import models.v1._
import org.scalatest.{FlatSpec, Matchers}

class HandMatcherSpec extends FlatSpec with Matchers with CardFixtures {

  val handMatcher = new HandMatcher(new HandExtractor)

  "HandMatcher" should "find straight flush" in {
    handMatcher.matchCards(straightFlushCards) shouldBe StraightFlush(List(Card(Jack, Clubs), Card(Ten, Clubs), Card(Nine, Clubs), Card(Eight, Clubs), Card(Seven, Clubs)))
  }

  it should "find four of a kind" in {
    handMatcher.matchCards(fourOfAKindCards) shouldBe FourOfAKind(List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Six, Spades)))
  }

  it should "find full house" in {
    handMatcher.matchCards(fullHouseCards) shouldBe FullHouse(List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Jack, Spades), Card(Jack, Hearts)))
  }

  it should "find flush" in {
    handMatcher.matchCards(flushCards) shouldBe Flush(List(Card(Queen, Clubs), Card(Ten, Clubs), Card(Nine, Clubs), Card(Eight, Clubs), Card(Seven, Clubs)))
  }

  it should "find straight" in {
    handMatcher.matchCards(straightCards) shouldBe Straight(List(Card(King, Diamonds), Card(Queen, Clubs), Card(Jack, Spades), Card(Ten, Hearts), Card(Nine, Spades)))
  }

  it should "find three of a kind" in {
    handMatcher.matchCards(threeOfAKindCards) shouldBe ThreeOfAKind(List(Card(King, Diamonds), Card(King, Clubs), Card(King, Spades)))
  }

  it should "find two pairs" in {
    handMatcher.matchCards(twoPairCards) shouldBe TwoPairs(List(Card(Queen, Hearts), Card(Queen, Clubs), Card(Eight, Hearts), Card(Eight, Spades)))
  }

  it should "find one pair" in {
    handMatcher.matchCards(onePairCards) shouldBe OnePair(List(Card(Seven, Spades), Card(Seven, Clubs)))
  }

  it should "find high card" in {
    handMatcher.matchCards(highCardCards) shouldBe HighCard(List(Card(Ace, Clubs), Card(King, Spades), Card(Seven, Spades), Card(Four, Hearts), Card(Three, Spades)))
  }
}
