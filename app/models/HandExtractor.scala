package models

import models.v1._

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

  def getStraightFlush(cards: List[Card]): Option[StraightFlush] = {
    if (areAllConsecutive(cards) &&
      areAllSameSuit(cards) &&
      cards.size == 5) {
      Some(StraightFlush(cards))
    } else {
      None
    }
  }

  def getFourOfAKind(cards: List[Card]): Option[FourOfAKind] = {
    cards.groupBy(_.rank).find(_._2.size == 4).map(cards => FourOfAKind(cards._2))
  }

  def getFullHouse(cards: List[Card]): Option[FullHouse] = {
    val cardsByRank = cards.groupBy(_.rank)

    cardsByRank.find(_._2.size == 3) match {
      case Some((rank, threeCards)) => cardsByRank.find(_._2.size == 2) match {
        case Some((r, twoCards)) => Some(FullHouse(threeCards ++ twoCards))
        case None                => None
      }
      case None => None
    }
  }

  def getFlush(cards: List[Card]): Option[Flush] = {
    if (getStraightFlush(cards).isEmpty) {
      cards.groupBy(_.suit).find(_._2.size == 5).map(c => Flush(c._2.sortedByRank))
    } else {
      None
    }
  }

  def isStraight(cards: List[Card]): Boolean = {
    getStraightFlush(cards).isEmpty &&
      areAllConsecutive(cards) &&
      cards.size == 5
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