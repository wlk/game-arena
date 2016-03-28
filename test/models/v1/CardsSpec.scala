package models.v1

import org.scalatest.{FlatSpec, Matchers}

class CardsSpec extends FlatSpec with Matchers {
  "Cards" should "check same suit cards" in {
    val aceOfSpaces = Card(Ace, Spades)
    val tenOfSpaces = Card(Ten, Spades)
    val twoOfHearts = Card(Two, Hearts)
    aceOfSpaces.isSameSuit(tenOfSpaces) shouldBe true
    twoOfHearts.isSameSuit(aceOfSpaces) shouldBe false
  }

  it should "pick greater card" in {
    val aceOfSpaces = Card(Ace, Spades)
    val tenOfSpaces = Card(Ten, Spades)
    val twoOfHearts = Card(Two, Hearts)

    (aceOfSpaces > tenOfSpaces) shouldBe true
    (twoOfHearts > tenOfSpaces) shouldBe false
  }
}
