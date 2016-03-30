package models

import models.v1._
import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

class HandExtractorSpec extends FlatSpec with Matchers {

  "HandExtractor" should "find straight flush" in {
    val cards = List(Card(Jack, Clubs), Card(Ten, Clubs), Card(Nine, Clubs), Card(Eight, Clubs), Card(Seven, Clubs))
    val handExtractor = new HandExtractor
    handExtractor.isStraightFlush(cards) shouldBe true
    handExtractor.isStraightFlush(Random.shuffle(cards)) shouldBe true

  }

  it should "not find straight flush" in {
    val handExtractor = new HandExtractor
    handExtractor.isStraightFlush(List(Card(Jack, Clubs), Card(Ten, Clubs), Card(Nine, Clubs), Card(Eight, Clubs))) shouldBe false

    handExtractor.isStraightFlush(List(Card(Jack, Clubs), Card(Ten, Clubs), Card(Nine, Clubs), Card(Eight, Clubs), Card(Seven, Hearts))) shouldBe false

    handExtractor.isStraightFlush(List(Card(Jack, Clubs), Card(Ten, Clubs), Card(Nine, Clubs), Card(Eight, Clubs), Card(Six, Clubs))) shouldBe false
  }

  it should "find four of a kind" in {
    val handExtractor = new HandExtractor
    val cards = List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Six, Spades), Card(Jack, Hearts))
    val only4Cards = List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Six, Spades))
    handExtractor.isFourOfAKind(cards) shouldBe true
    handExtractor.isFourOfAKind(only4Cards) shouldBe true
  }

  it should "not find four of a kind" in {
    val handExtractor = new HandExtractor
    val cards = List(Card(Six, Clubs), Card(Five, Diamonds), Card(Six, Hearts), Card(Six, Spades), Card(Jack, Hearts))
    val only4Cards = List(Card(Six, Clubs), Card(Five, Diamonds), Card(Six, Hearts), Card(Six, Spades))
    handExtractor.isFourOfAKind(cards) shouldBe false
    handExtractor.isFourOfAKind(only4Cards) shouldBe false

    handExtractor.isFourOfAKind(List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts))) shouldBe false
  }

  it should "find full house" in {
    val handExtractor = new HandExtractor
    val cards = List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Jack, Spades), Card(Jack, Hearts))
    handExtractor.isFullHouse(cards) shouldBe true
    handExtractor.isFullHouse(Random.shuffle(cards)) shouldBe true
  }

  it should "not find full house" in {
    val handExtractor = new HandExtractor
    val cards = List(Card(Six, Clubs), Card(Six, Diamonds), Card(Six, Hearts), Card(Six, Spades), Card(Jack, Hearts))
    handExtractor.isFullHouse(cards) shouldBe false
    handExtractor.isFullHouse(Random.shuffle(cards)) shouldBe false

    handExtractor.isFullHouse(List(Card(Six, Clubs), Card(Six, Diamonds), Card(Jack, Spades), Card(Jack, Hearts))) shouldBe false
  }

}
