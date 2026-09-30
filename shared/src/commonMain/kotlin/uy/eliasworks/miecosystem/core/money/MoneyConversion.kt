package uy.eliasworks.miecosystem.core.money

data class MoneyConversion(
    val original: Money,
    val converted: Money,
    val rate: ExchangeRate
) {
    companion object {
        fun execute(money: Money, rate: ExchangeRate): MoneyConversion {
            val converted = rate.convert(money)
            return MoneyConversion(money, converted, rate)
        }
    }
}
