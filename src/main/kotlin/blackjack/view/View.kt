package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.model.Players

object View {

    fun inputPlayerNames(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return Players(
            readln()
                .split(",")
                .map { Player(it.trim()) }
        )
    }

    fun initialCardDealingComment(players: Players) {
        println("\n딜러와 ${players.values.joinToString { it.name }}에게 2장의 나누었습니다.")
    }

    fun dealerMoreCardComment(hitted: Boolean) {
        if (hitted) {
            println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
        } else {
            println("\n딜러는 17이상이라 카드를 받지 않았습니다.\n")
        }
    }

    private fun showCards(players: Players) {
        players.values.forEach { showCard(it) }
    }

    fun showCard(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString { it.cardName() }}")
    }

    private fun showDealerOneCard(dealer: Dealer) {
        println("${dealer.name}: ${dealer.cards.first().cardName()}")
    }

    fun showCards(participants: Participants) {
        showDealerOneCard(participants.dealer)
        showCards(participants.players)
        println()
    }

    fun hitOrStand(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return yesOrNo()
    }

    fun showResults(participants: Participants) {
        showResult(participants.dealer)
        showResults(participants.players)
        println("\n## 최종 승패")
        showDealerWinAndLose(participants.dealer)
        showWinAndLose(participants.players)
    }

    private fun showResults(players: Players) {
        players.values.forEach { showResult(it) }
    }

    private fun showResult(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString { it.cardName() }} - 결과: ${player.blackJackResult?.point}")
    }

    private fun showDealerWinAndLose(dealer: Dealer) {
        println("${dealer.name}: ${dealer.blackJackResult?.winning}승 ${dealer.blackJackResult?.losing}패")
    }

    private fun showWinAndLose(players: Players) {
        players.values.forEach { showWinAndLose(it) }
    }

    private fun showWinAndLose(player: Player) {
        println("${player.name}: ${if ((player.blackJackResult?.winning ?: 0) > 0) "승" else "패"}")
    }

    private fun yesOrNo(): Boolean = when (readln()) {
        "y" -> true
        "n" -> false
        else -> {
            println("잘못된 입력입니다. 다시 입력해주세요.")
            yesOrNo()
        }
    }
}
