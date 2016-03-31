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
    cards.groupBy(_.rank).find {
      case (_, c) => c.size == 4
    }.map(cards => FourOfAKind(cards._2))
  }

  def getFullHouse(cards: List[Card]): Option[FullHouse] = {
    val cardsByRank = cards.groupBy(_.rank)

    cardsByRank.find {
      case (_, c) => c.size == 3
    } match {
      case Some((_, threeCards)) => cardsByRank.find(_._2.size == 2) match {
        case Some((_, twoCards)) => Some(FullHouse(threeCards ++ twoCards))
        case None                => None
      }
      case None => None
    }
  }

  def getFlush(cards: List[Card]): Option[Flush] = {
    if (getStraightFlush(cards).isEmpty) {
      cards.groupBy(_.suit).find {
        case (_, c) => c.size == 5
      }.map(c => Flush(c._2.sortedByRank))
    } else {
      None
    }
  }

  def getStraight(cards: List[Card]): Option[Straight] = {
    if (getStraightFlush(cards).isEmpty && areAllConsecutive(cards) && cards.size == 5) {
      Some(Straight(cards.sortedByRank))
    } else {
      None
    }
  }

  def getThreeOfAKind(cards: List[Card]): Option[ThreeOfAKind] = {
    val cardsGroupedByRank = cards.groupBy(_.rank)
    if (cardsGroupedByRank.mapValues(_.size).values.toList.sorted == List(1, 1, 3)) {
      cardsGroupedByRank.find {
        case (_, c) => c.size == 3
      }.map {
        case (_, c) => ThreeOfAKind(c)
      }
    } else {
      None
    }

  }

  def getTwoPairs(cards: List[Card]): Option[TwoPairs] = {
    val cardsGroupedByRank = cards.groupBy(_.rank)
    if (cardsGroupedByRank.mapValues(_.size).values.toList.sorted == List(1, 2, 2)) {
      val c = cardsGroupedByRank.filter {
        case (_, groupedCards) => groupedCards.size == 2
      }.values.flatten.toList
      Some(TwoPairs(c.sortedByRank))
    } else {
      None
    }
  }

  def getOnePair(cards: List[Card]): Option[OnePair] = {
    val cardsGroupedByRank = cards.groupBy(_.rank)
    if (cardsGroupedByRank.mapValues(_.size).values.toList.sorted == List(1, 1, 1, 2)) {
      cardsGroupedByRank.find {
        case (_, c) => c.size == 2
      }.map {
        case (_, c) => OnePair(c)
      }
    } else {
      None
    }
  }
}