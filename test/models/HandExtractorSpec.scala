package models

import models.v1._
import org.scalatest.{FlatSpec, Matchers}

class HandExtractorSpec extends FlatSpec with Matchers {

  val handExtractor = new HandExtractor

  val straightFlushCards = List(Card(Jack, Clubs), Card(Ten, Clubs), Card(Nine, Clubs), Card(Eight, Clubs), Card(Seven, Clubs))
  val fourOfAKindCards = List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Six, Spades), Card(Jack, Hearts))
  val fullHouseCards = List(Card(Jack, Spades), Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Jack, Hearts))
  val flushCards = List(Card(Seven, Clubs), Card(Nine, Clubs), Card(Queen, Clubs), Card(Ten, Clubs), Card(Eight, Clubs))
  val straightCards = List(Card(Queen, Clubs), Card(King, Diamonds), Card(Jack, Spades), Card(Ten, Hearts), Card(Nine, Spades))
  val threeOfAKindCards = List(Card(King, Diamonds), Card(King, Clubs), Card(Ten, Spades), Card(Nine, Hearts), Card(King, Spades))
  val twoPairCards = List(Card(Queen, Hearts), Card(Two, Clubs), Card(Eight, Hearts), Card(Eight, Spades), Card(Queen, Clubs))
  val onePairCards = List(Card(Seven, Spades), Card(Four, Hearts), Card(King, Spades), Card(Seven, Clubs), Card(Three, Spades))

  "HandExtractor" should "find straight flush" in {
    handExtractor.getStraightFlush(straightFlushCards) shouldBe Some(StraightFlush(straightFlushCards))
    handExtractor.getFourOfAKind(straightFlushCards) shouldBe None
    handExtractor.getFullHouse(straightFlushCards) shouldBe None
    handExtractor.getFlush(straightFlushCards) shouldBe None
    handExtractor.getStraight(straightFlushCards) shouldBe None
    handExtractor.getThreeOfAKind(straightFlushCards) shouldBe None
    handExtractor.getTwoPairs(straightFlushCards) shouldBe None
    handExtractor.getOnePair(straightFlushCards) shouldBe None
  }

  it should "find four of a kind" in {
    handExtractor.getStraightFlush(fourOfAKindCards) shouldBe None
    handExtractor.getFourOfAKind(fourOfAKindCards) shouldBe Some(FourOfAKind(List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Six, Spades))))
    handExtractor.getFullHouse(fourOfAKindCards) shouldBe None
    handExtractor.getFlush(fourOfAKindCards) shouldBe None
    handExtractor.getStraight(fourOfAKindCards) shouldBe None
    handExtractor.getThreeOfAKind(fourOfAKindCards) shouldBe None
    handExtractor.getTwoPairs(fourOfAKindCards) shouldBe None
    handExtractor.getOnePair(fourOfAKindCards) shouldBe None
  }

  it should "find full house" in {
    handExtractor.getStraightFlush(fullHouseCards) shouldBe None
    handExtractor.getFourOfAKind(fullHouseCards) shouldBe None
    handExtractor.getFullHouse(fullHouseCards) shouldBe Some(FullHouse(List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Jack, Spades), Card(Jack, Hearts))))
    handExtractor.getFlush(fullHouseCards) shouldBe None
    handExtractor.getStraight(fullHouseCards) shouldBe None
    handExtractor.getThreeOfAKind(fullHouseCards) shouldBe None
    handExtractor.getTwoPairs(fullHouseCards) shouldBe None
    handExtractor.getOnePair(fullHouseCards) shouldBe None
  }

  it should "find flush" in {
    handExtractor.getStraightFlush(flushCards) shouldBe None
    handExtractor.getFourOfAKind(flushCards) shouldBe None
    handExtractor.getFullHouse(flushCards) shouldBe None
    handExtractor.getFlush(flushCards) shouldBe Some(Flush(List(Card(Queen, Clubs), Card(Ten, Clubs), Card(Nine, Clubs), Card(Eight, Clubs), Card(Seven, Clubs))))
    handExtractor.getStraight(flushCards) shouldBe None
    handExtractor.getThreeOfAKind(flushCards) shouldBe None
    handExtractor.getTwoPairs(flushCards) shouldBe None
    handExtractor.getOnePair(flushCards) shouldBe None
  }

  it should "find straight" in {
    handExtractor.getStraightFlush(straightCards) shouldBe None
    handExtractor.getFourOfAKind(straightCards) shouldBe None
    handExtractor.getFullHouse(straightCards) shouldBe None
    handExtractor.getFlush(straightCards) shouldBe None
    handExtractor.getStraight(straightCards) shouldBe Some(Straight(List(Card(King, Diamonds), Card(Queen, Clubs), Card(Jack, Spades), Card(Ten, Hearts), Card(Nine, Spades))))
    handExtractor.getThreeOfAKind(straightCards) shouldBe None
    handExtractor.getTwoPairs(straightCards) shouldBe None
    handExtractor.getOnePair(straightCards) shouldBe None
  }

  it should "find three of a kind" in {
    handExtractor.getStraightFlush(threeOfAKindCards) shouldBe None
    handExtractor.getFourOfAKind(threeOfAKindCards) shouldBe None
    handExtractor.getFullHouse(threeOfAKindCards) shouldBe None
    handExtractor.getFlush(threeOfAKindCards) shouldBe None
    handExtractor.getStraight(threeOfAKindCards) shouldBe None
    handExtractor.getThreeOfAKind(threeOfAKindCards) shouldBe Some(ThreeOfAKind(List(Card(King, Diamonds), Card(King, Clubs), Card(King, Spades))))
    handExtractor.getTwoPairs(threeOfAKindCards) shouldBe None
    handExtractor.getOnePair(threeOfAKindCards) shouldBe None
  }

  it should "find two pair" in {
    handExtractor.getStraightFlush(twoPairCards) shouldBe None
    handExtractor.getFourOfAKind(twoPairCards) shouldBe None
    handExtractor.getFullHouse(twoPairCards) shouldBe None
    handExtractor.getFlush(twoPairCards) shouldBe None
    handExtractor.getStraight(twoPairCards) shouldBe None
    handExtractor.getThreeOfAKind(twoPairCards) shouldBe None
    handExtractor.getTwoPairs(twoPairCards) shouldBe Some(TwoPairs(List(Card(Queen, Hearts), Card(Queen, Clubs), Card(Eight, Hearts), Card(Eight, Spades))))
    handExtractor.getOnePair(twoPairCards) shouldBe None
  }

  it should "find one pair" in {
    handExtractor.getStraightFlush(onePairCards) shouldBe None
    handExtractor.getFourOfAKind(onePairCards) shouldBe None
    handExtractor.getFullHouse(onePairCards) shouldBe None
    handExtractor.getFlush(onePairCards) shouldBe None
    handExtractor.getStraight(onePairCards) shouldBe None
    handExtractor.getThreeOfAKind(onePairCards) shouldBe None
    handExtractor.getTwoPairs(onePairCards) shouldBe None
    handExtractor.getOnePair(onePairCards) shouldBe Some(OnePair(List(Card(Seven, Spades), Card(Seven, Clubs))))
  }

}
