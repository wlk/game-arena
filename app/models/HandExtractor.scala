package models

import models.v1.Card

/*
  Responsible for extracting information about what hands can be arranged from a list of cards.
 */
class HandExtractor {

  private def areAllConsecutive(cards: List[Card]): Boolean = {
    val cardsMappedToRankIndexes = cards.map(card => models.v1.Rank.ranks.indexOf(card.rank)).sorted.reverse
    val consecutiveCardPairs = cardsMappedToRankIndexes.sliding(2)

    consecutiveCardPairs.forall {
      case first :: second :: _ => first - second == 1
      case _                    => false
    }
  }

  private def areAllSameSuit(cards: List[Card]): Boolean = cards.map(_.suit).distinct.size == 1

  def isStraightFlush(cards: List[Card]): Boolean = {
    areAllConsecutive(cards) &&
      areAllSameSuit(cards) &&
      cards.size == 5
  }

  def isFourOfAKind(cards: List[Card]): Boolean = {
    cards.groupBy(_.rank).mapValues(_.size).exists(_._2 == 4)
  }

  def isFullHouse(cards: List[Card]): Boolean = {
    cards.groupBy(_.rank).mapValues(_.size).values.toList.sorted == List(2, 3)
  }

  def isFlush(cards: List[Card]): Boolean = {
    !isStraightFlush(cards) &&
      cards.groupBy(_.suit).mapValues(_.size).exists(_._2 == 5)
  }

  def isStraight(cards: List[Card]): Boolean = {
    areAllConsecutive(cards) &&
      cards.size == 5 &&
      !isStraightFlush(cards)
  }

  def isThreeOfAKind(cards: List[Card]): Boolean = {
    cards.groupBy(_.rank).mapValues(_.size).values.toList.sorted == List(1, 1, 3)
  }

  def isTwoPair(cards: List[Card]): Boolean = {
    cards.groupBy(_.rank).mapValues(_.size).values.toList.sorted == List(1, 2, 2)
  }

  def isOnePair(cards: List[Card]): Boolean = {
    cards.groupBy(_.rank).mapValues(_.size).values.toList.sorted == List(1, 1, 1, 2)
  }

}