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
    val aceOfDiamonds = Card(Ace, Diamonds)

    aceOfSpaces > tenOfSpaces shouldBe true
    twoOfHearts > tenOfSpaces shouldBe false

    aceOfSpaces > aceOfDiamonds shouldBe false
    aceOfDiamonds > aceOfSpaces shouldBe false
  }

  it should "match cards in pairs" in {
    val aceOfSpaces = Card(Ace, Spades)
    val tenOfSpaces = Card(Ten, Spades)
    val twoOfHearts = Card(Two, Hearts)
    val aceOfDiamonds = Card(Ace, Diamonds)

    aceOfDiamonds.isPairWith(aceOfSpaces) shouldBe true
    aceOfSpaces.isPairWith(aceOfDiamonds) shouldBe true

    twoOfHearts.isPairWith(tenOfSpaces) shouldBe false
  }

  it should "shuffled deck should contain the same cards as not shuffled" in {
    val deck = Deck.fullDeck
    val shuffledDeck = deck.shuffle

    shuffledDeck.cards should contain theSameElementsAs deck.cards
  }

  it should "match cards in suits" in {
    val aceOfSpaces = Card(Ace, Spades)
    val twoOfSpaces = Card(Two, Spades)
    val aceOfDiamonds = Card(Ace, Diamonds)

    aceOfDiamonds.isSameSuit(aceOfSpaces) shouldBe false
    aceOfSpaces.isSameSuit(aceOfDiamonds) shouldBe false

    aceOfSpaces.isSameSuit(twoOfSpaces) shouldBe true
    twoOfSpaces.isSameSuit(aceOfSpaces) shouldBe true
  }

  it should "remove drawn card from the deck" in {
    val deck = Deck.fullDeck.shuffle

    val (card, deckAfterDrawing) = deck.draw

    deckAfterDrawing.cards should not contain card
  }

  it should "detect invalid decks" in {
    val deck = Deck.fullDeck
    deck.isValid shouldBe true

    deck.shuffle.isValid shouldBe true

    an[IllegalArgumentException] should be thrownBy Deck(List(Card(Ace, Spades), Card(Ace, Spades)))
  }
}
