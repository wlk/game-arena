package models

import models.v1._
import org.scalatest.{FlatSpec, Matchers}

class HandExtractorSpec extends FlatSpec with Matchers {

  "HandExtractor" should "find straight flush" in {
    val cards = List(Card(Jack, Clubs), Card(Ten, Clubs), Card(Nine, Clubs), Card(Eight, Clubs), Card(Seven, Clubs))
    val handExtractor = new HandExtractor
    handExtractor.isStraightFlush(cards) shouldBe true
    handExtractor.isFourOfAKind(cards) shouldBe false
    handExtractor.isFullHouse(cards) shouldBe false
    handExtractor.isFlush(cards) shouldBe false
    handExtractor.isStraight(cards) shouldBe false
    handExtractor.isThreeOfAKind(cards) shouldBe false
    handExtractor.isTwoPair(cards) shouldBe false
    handExtractor.isOnePair(cards) shouldBe false
  }

  it should "find four of a kind" in {
    val handExtractor = new HandExtractor
    val cards = List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Six, Spades), Card(Jack, Hearts))
    handExtractor.isStraightFlush(cards) shouldBe false
    handExtractor.isFourOfAKind(cards) shouldBe true
    handExtractor.isFullHouse(cards) shouldBe false
    handExtractor.isFlush(cards) shouldBe false
    handExtractor.isStraight(cards) shouldBe false
    handExtractor.isThreeOfAKind(cards) shouldBe false
    handExtractor.isTwoPair(cards) shouldBe false
    handExtractor.isOnePair(cards) shouldBe false
  }

  it should "find full house" in {
    val handExtractor = new HandExtractor
    val cards = List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Jack, Spades), Card(Jack, Hearts))
    handExtractor.isStraightFlush(cards) shouldBe false
    handExtractor.isFourOfAKind(cards) shouldBe false
    handExtractor.isFullHouse(cards) shouldBe true
    handExtractor.isFlush(cards) shouldBe false
    handExtractor.isStraight(cards) shouldBe false
    handExtractor.isThreeOfAKind(cards) shouldBe false
    handExtractor.isTwoPair(cards) shouldBe false
    handExtractor.isOnePair(cards) shouldBe false
  }

  it should "find flush" in {
    val cards = List(Card(Queen, Clubs), Card(Ten, Clubs), Card(Nine, Clubs), Card(Eight, Clubs), Card(Seven, Clubs))
    val handExtractor = new HandExtractor
    handExtractor.isStraightFlush(cards) shouldBe false
    handExtractor.isFourOfAKind(cards) shouldBe false
    handExtractor.isFullHouse(cards) shouldBe false
    handExtractor.isFlush(cards) shouldBe true
    handExtractor.isStraight(cards) shouldBe false
    handExtractor.isThreeOfAKind(cards) shouldBe false
    handExtractor.isTwoPair(cards) shouldBe false
    handExtractor.isOnePair(cards) shouldBe false
  }

  it should "find straight" in {
    val cards = List(Card(King, Diamonds), Card(Queen, Clubs), Card(Jack, Spades), Card(Ten, Hearts), Card(Nine, Spades))
    val handExtractor = new HandExtractor
    handExtractor.isStraightFlush(cards) shouldBe false
    handExtractor.isFourOfAKind(cards) shouldBe false
    handExtractor.isFullHouse(cards) shouldBe false
    handExtractor.isFlush(cards) shouldBe false
    handExtractor.isStraight(cards) shouldBe true
    handExtractor.isThreeOfAKind(cards) shouldBe false
    handExtractor.isTwoPair(cards) shouldBe false
    handExtractor.isOnePair(cards) shouldBe false
  }

  it should "find three of a kind" in {
    val cards = List(Card(King, Diamonds), Card(King, Clubs), Card(King, Spades), Card(Ten, Hearts), Card(Nine, Spades))
    val handExtractor = new HandExtractor
    handExtractor.isStraightFlush(cards) shouldBe false
    handExtractor.isFourOfAKind(cards) shouldBe false
    handExtractor.isFullHouse(cards) shouldBe false
    handExtractor.isFlush(cards) shouldBe false
    handExtractor.isStraight(cards) shouldBe false
    handExtractor.isThreeOfAKind(cards) shouldBe true
    handExtractor.isTwoPair(cards) shouldBe false
    handExtractor.isTwoPair(cards) shouldBe false
    handExtractor.isOnePair(cards) shouldBe false
  }

  it should "find two pair" in {
    val cards = List(Card(Queen, Hearts), Card(Queen, Clubs), Card(Eight, Hearts), Card(Eight, Spades), Card(Two, Clubs))
    val handExtractor = new HandExtractor
    handExtractor.isStraightFlush(cards) shouldBe false
    handExtractor.isFourOfAKind(cards) shouldBe false
    handExtractor.isFullHouse(cards) shouldBe false
    handExtractor.isFlush(cards) shouldBe false
    handExtractor.isStraight(cards) shouldBe false
    handExtractor.isThreeOfAKind(cards) shouldBe false
    handExtractor.isTwoPair(cards) shouldBe true
    handExtractor.isOnePair(cards) shouldBe false
  }

  it should "find one pair" in {
    val cards = List(Card(Seven, Spades), Card(Seven, Hearts), Card(King, Spades), Card(Four, Clubs), Card(Three, Spades))
    val handExtractor = new HandExtractor
    handExtractor.isStraightFlush(cards) shouldBe false
    handExtractor.isFourOfAKind(cards) shouldBe false
    handExtractor.isFullHouse(cards) shouldBe false
    handExtractor.isFlush(cards) shouldBe false
    handExtractor.isStraight(cards) shouldBe false
    handExtractor.isThreeOfAKind(cards) shouldBe false
    handExtractor.isTwoPair(cards) shouldBe false
    handExtractor.isOnePair(cards) shouldBe true
  }

}
