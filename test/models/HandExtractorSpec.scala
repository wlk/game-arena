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

}
