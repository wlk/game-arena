package models

import models.v1.Card

case class Player(id: Int, name: String, status: String, version: String, stack: Int, bet: Int, holeCards: List[Card])

case class GameState(tournamentId: String, gameId: String, round: Int, betIndex: Int, smallBlind: Int, currentBuyIn: Int,
                     pot: Int, minimumRaise: Int, dealer: Int, orbits: Int, inAction: Int, players: List[Player], communityCards: List[Card])
