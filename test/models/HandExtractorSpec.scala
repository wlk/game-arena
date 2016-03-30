package models

import models.v1._
import org.scalatest.{FlatSpec, Matchers}

class HandExtractorSpec extends FlatSpec with Matchers {

  val handExtractor = new HandExtractor

  val straightFlushCards = List(Card(Jack, Clubs), Card(Ten, Clubs), Card(Nine, Clubs), Card(Eight, Clubs), Card(Seven, Clubs))
  val fourOfAKindCards = List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Six, Spades), Card(Jack, Hearts))
  val fullHouseCards = List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Jack, Spades), Card(Jack, Hearts))
  val flushCards = List(Card(Queen, Clubs), Card(Ten, Clubs), Card(Nine, Clubs), Card(Eight, Clubs), Card(Seven, Clubs))
  val straightCards = List(Card(King, Diamonds), Card(Queen, Clubs), Card(Jack, Spades), Card(Ten, Hearts), Card(Nine, Spades))
  val threeOfAKind = List(Card(King, Diamonds), Card(King, Clubs), Card(King, Spades), Card(Ten, Hearts), Card(Nine, Spades))
  val twoPairCards = List(Card(Queen, Hearts), Card(Queen, Clubs), Card(Eight, Hearts), Card(Eight, Spades), Card(Two, Clubs))
  val onePairCards = List(Card(Seven, Spades), Card(Seven, Hearts), Card(King, Spades), Card(Four, Clubs), Card(Three, Spades))

  "HandExtractor" should "find straight flush" in {
    handExtractor.getStraightFlush(straightFlushCards) shouldBe Some(StraightFlush(straightFlushCards))
    handExtractor.isFourOfAKind(straightFlushCards) shouldBe None
    handExtractor.isFullHouse(straightFlushCards) shouldBe false
    handExtractor.isFlush(straightFlushCards) shouldBe false
    handExtractor.isStraight(straightFlushCards) shouldBe false
    handExtractor.isThreeOfAKind(straightFlushCards) shouldBe false
    handExtractor.isTwoPair(straightFlushCards) shouldBe false
    handExtractor.isOnePair(straightFlushCards) shouldBe false
  }

  it should "find four of a kind" in {
    handExtractor.getStraightFlush(fourOfAKindCards) shouldBe None
    handExtractor.isFourOfAKind(fourOfAKindCards) shouldBe Some(FourOfAKind(List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Six, Spades))))
    handExtractor.isFullHouse(fourOfAKindCards) shouldBe false
    handExtractor.isFlush(fourOfAKindCards) shouldBe false
    handExtractor.isStraight(fourOfAKindCards) shouldBe false
    handExtractor.isThreeOfAKind(fourOfAKindCards) shouldBe false
    handExtractor.isTwoPair(fourOfAKindCards) shouldBe false
    handExtractor.isOnePair(fourOfAKindCards) shouldBe false
  }

  it should "find full house" in {
    handExtractor.getStraightFlush(fullHouseCards) shouldBe None
    handExtractor.isFourOfAKind(fullHouseCards) shouldBe None
    handExtractor.isFullHouse(fullHouseCards) shouldBe true
    handExtractor.isFlush(fullHouseCards) shouldBe false
    handExtractor.isStraight(fullHouseCards) shouldBe false
    handExtractor.isThreeOfAKind(fullHouseCards) shouldBe false
    handExtractor.isTwoPair(fullHouseCards) shouldBe false
    handExtractor.isOnePair(fullHouseCards) shouldBe false
  }

  it should "find flush" in {
    handExtractor.getStraightFlush(flushCards) shouldBe None
    handExtractor.isFourOfAKind(flushCards) shouldBe None
    handExtractor.isFullHouse(flushCards) shouldBe false
    handExtractor.isFlush(flushCards) shouldBe true
    handExtractor.isStraight(flushCards) shouldBe false
    handExtractor.isThreeOfAKind(flushCards) shouldBe false
    handExtractor.isTwoPair(flushCards) shouldBe false
    handExtractor.isOnePair(flushCards) shouldBe false
  }

  it should "find straight" in {
    handExtractor.getStraightFlush(straightCards) shouldBe None
    handExtractor.isFourOfAKind(straightCards) shouldBe None
    handExtractor.isFullHouse(straightCards) shouldBe false
    handExtractor.isFlush(straightCards) shouldBe false
    handExtractor.isStraight(straightCards) shouldBe true
    handExtractor.isThreeOfAKind(straightCards) shouldBe false
    handExtractor.isTwoPair(straightCards) shouldBe false
    handExtractor.isOnePair(straightCards) shouldBe false
  }

  it should "find three of a kind" in {
    handExtractor.getStraightFlush(threeOfAKind) shouldBe None
    handExtractor.isFourOfAKind(threeOfAKind) shouldBe None
    handExtractor.isFullHouse(threeOfAKind) shouldBe false
    handExtractor.isFlush(threeOfAKind) shouldBe false
    handExtractor.isStraight(threeOfAKind) shouldBe false
    handExtractor.isThreeOfAKind(threeOfAKind) shouldBe true
    handExtractor.isTwoPair(threeOfAKind) shouldBe false
    handExtractor.isTwoPair(threeOfAKind) shouldBe false
    handExtractor.isOnePair(threeOfAKind) shouldBe false
  }

  it should "find two pair" in {
    handExtractor.getStraightFlush(twoPairCards) shouldBe None
    handExtractor.isFourOfAKind(twoPairCards) shouldBe None
    handExtractor.isFullHouse(twoPairCards) shouldBe false
    handExtractor.isFlush(twoPairCards) shouldBe false
    handExtractor.isStraight(twoPairCards) shouldBe false
    handExtractor.isThreeOfAKind(twoPairCards) shouldBe false
    handExtractor.isTwoPair(twoPairCards) shouldBe true
    handExtractor.isOnePair(twoPairCards) shouldBe false
  }

  it should "find one pair" in {
    handExtractor.getStraightFlush(onePairCards) shouldBe None
    handExtractor.isFourOfAKind(onePairCards) shouldBe None
    handExtractor.isFullHouse(onePairCards) shouldBe false
    handExtractor.isFlush(onePairCards) shouldBe false
    handExtractor.isStraight(onePairCards) shouldBe false
    handExtractor.isThreeOfAKind(onePairCards) shouldBe false
    handExtractor.isTwoPair(onePairCards) shouldBe false
    handExtractor.isOnePair(onePairCards) shouldBe true
  }

}
