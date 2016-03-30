package models

import models.v1._
import org.scalatest.{FlatSpec, Matchers}

class HandExtractorSpec extends FlatSpec with Matchers {

  val handExtractor = new HandExtractor

  val straightFlushCards = List(Card(Jack, Clubs), Card(Ten, Clubs), Card(Nine, Clubs), Card(Eight, Clubs), Card(Seven, Clubs))
  val fourOfAKindCards = List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Six, Spades), Card(Jack, Hearts))
  val fullHouseCards = List(Card(Jack, Spades), Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Jack, Hearts))
  val flushCards = List(Card(Seven, Clubs), Card(Nine, Clubs), Card(Queen, Clubs), Card(Ten, Clubs), Card(Eight, Clubs))
  val straightCards = List(Card(King, Diamonds), Card(Queen, Clubs), Card(Jack, Spades), Card(Ten, Hearts), Card(Nine, Spades))
  val threeOfAKind = List(Card(King, Diamonds), Card(King, Clubs), Card(King, Spades), Card(Ten, Hearts), Card(Nine, Spades))
  val twoPairCards = List(Card(Queen, Hearts), Card(Queen, Clubs), Card(Eight, Hearts), Card(Eight, Spades), Card(Two, Clubs))
  val onePairCards = List(Card(Seven, Spades), Card(Seven, Hearts), Card(King, Spades), Card(Four, Clubs), Card(Three, Spades))

  "HandExtractor" should "find straight flush" in {
    handExtractor.getStraightFlush(straightFlushCards) shouldBe Some(StraightFlush(straightFlushCards))
    handExtractor.getFourOfAKind(straightFlushCards) shouldBe None
    handExtractor.getFullHouse(straightFlushCards) shouldBe None
    handExtractor.getFlush(straightFlushCards) shouldBe None
    handExtractor.isStraight(straightFlushCards) shouldBe false
    handExtractor.isThreeOfAKind(straightFlushCards) shouldBe false
    handExtractor.isTwoPair(straightFlushCards) shouldBe false
    handExtractor.isOnePair(straightFlushCards) shouldBe false
  }

  it should "find four of a kind" in {
    handExtractor.getStraightFlush(fourOfAKindCards) shouldBe None
    handExtractor.getFourOfAKind(fourOfAKindCards) shouldBe Some(FourOfAKind(List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Six, Spades))))
    handExtractor.getFullHouse(fourOfAKindCards) shouldBe None
    handExtractor.getFlush(fourOfAKindCards) shouldBe None
    handExtractor.isStraight(fourOfAKindCards) shouldBe false
    handExtractor.isThreeOfAKind(fourOfAKindCards) shouldBe false
    handExtractor.isTwoPair(fourOfAKindCards) shouldBe false
    handExtractor.isOnePair(fourOfAKindCards) shouldBe false
  }

  it should "find full house" in {
    handExtractor.getStraightFlush(fullHouseCards) shouldBe None
    handExtractor.getFourOfAKind(fullHouseCards) shouldBe None
    handExtractor.getFullHouse(fullHouseCards) shouldBe Some(FullHouse(List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Jack, Spades), Card(Jack, Hearts))))
    handExtractor.getFlush(fullHouseCards) shouldBe None
    handExtractor.isStraight(fullHouseCards) shouldBe false
    handExtractor.isThreeOfAKind(fullHouseCards) shouldBe false
    handExtractor.isTwoPair(fullHouseCards) shouldBe false
    handExtractor.isOnePair(fullHouseCards) shouldBe false
  }

  it should "find flush" in {
    handExtractor.getStraightFlush(flushCards) shouldBe None
    handExtractor.getFourOfAKind(flushCards) shouldBe None
    handExtractor.getFullHouse(flushCards) shouldBe None
    handExtractor.getFlush(flushCards) shouldBe Some(Flush(List(Card(Queen, Clubs), Card(Ten, Clubs), Card(Nine, Clubs), Card(Eight, Clubs), Card(Seven, Clubs))))
    handExtractor.isStraight(flushCards) shouldBe false
    handExtractor.isThreeOfAKind(flushCards) shouldBe false
    handExtractor.isTwoPair(flushCards) shouldBe false
    handExtractor.isOnePair(flushCards) shouldBe false
  }

  it should "find straight" in {
    handExtractor.getStraightFlush(straightCards) shouldBe None
    handExtractor.getFourOfAKind(straightCards) shouldBe None
    handExtractor.getFullHouse(straightCards) shouldBe None
    handExtractor.getFlush(straightCards) shouldBe None
    handExtractor.isStraight(straightCards) shouldBe true
    handExtractor.isThreeOfAKind(straightCards) shouldBe false
    handExtractor.isTwoPair(straightCards) shouldBe false
    handExtractor.isOnePair(straightCards) shouldBe false
  }

  it should "find three of a kind" in {
    handExtractor.getStraightFlush(threeOfAKind) shouldBe None
    handExtractor.getFourOfAKind(threeOfAKind) shouldBe None
    handExtractor.getFullHouse(threeOfAKind) shouldBe None
    handExtractor.getFlush(threeOfAKind) shouldBe None
    handExtractor.isStraight(threeOfAKind) shouldBe false
    handExtractor.isThreeOfAKind(threeOfAKind) shouldBe true
    handExtractor.isTwoPair(threeOfAKind) shouldBe false
    handExtractor.isTwoPair(threeOfAKind) shouldBe false
    handExtractor.isOnePair(threeOfAKind) shouldBe false
  }

  it should "find two pair" in {
    handExtractor.getStraightFlush(twoPairCards) shouldBe None
    handExtractor.getFourOfAKind(twoPairCards) shouldBe None
    handExtractor.getFullHouse(twoPairCards) shouldBe None
    handExtractor.getFlush(twoPairCards) shouldBe None
    handExtractor.isStraight(twoPairCards) shouldBe false
    handExtractor.isThreeOfAKind(twoPairCards) shouldBe false
    handExtractor.isTwoPair(twoPairCards) shouldBe true
    handExtractor.isOnePair(twoPairCards) shouldBe false
  }

  it should "find one pair" in {
    handExtractor.getStraightFlush(onePairCards) shouldBe None
    handExtractor.getFourOfAKind(onePairCards) shouldBe None
    handExtractor.getFullHouse(onePairCards) shouldBe None
    handExtractor.getFlush(onePairCards) shouldBe None
    handExtractor.isStraight(onePairCards) shouldBe false
    handExtractor.isThreeOfAKind(onePairCards) shouldBe false
    handExtractor.isTwoPair(onePairCards) shouldBe false
    handExtractor.isOnePair(onePairCards) shouldBe true
  }

}
