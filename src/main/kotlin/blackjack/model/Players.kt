package blackjack.model

class Players(val values: List<Player>) {

    fun initialCardDealing(dealer: Dealer) {
        values.forEach {
            it.addCards(dealer.dealingTwoCards())
        }
    }

    fun hitablePlayers(): List<Player> =
        values.filter { it.hit }
}
