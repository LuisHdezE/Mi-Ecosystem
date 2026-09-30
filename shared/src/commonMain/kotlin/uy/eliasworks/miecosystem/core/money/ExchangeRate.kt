package uy.eliasworks.miecosystem.core.money

import kotlin.math.abs

class ExchangeRate(
    val fromCurrency: Currency,
    val toCurrency: Currency,
    val rateNumerator: Long,
    val rateDenominator: Long = 1L
) {
    init {
        require(fromCurrency != toCurrency) { "Source and target currencies must differ" }
        require(rateNumerator > 0 && rateDenominator > 0) { "Rate must be strictly positive" }
    }

    fun convert(money: Money): Money {
        require(money.currency == fromCurrency) { "Wrong source currency" }

        val powerFrom = pow10(fromCurrency.minorUnits)
        val powerTo = pow10(toCurrency.minorUnits)
        
        val numerator = money.amountMinorUnits * rateNumerator * powerTo
        val denominator = rateDenominator * powerFrom
        
        val result = divideHalfUp(numerator, denominator)
        
        return Money(result, toCurrency)
    }

    private fun divideHalfUp(num: Long, den: Long): Long {
        val sign = if ((num < 0L) xor (den < 0L)) -1L else 1L
        val absNum = abs(num)
        val absDen = abs(den)
        
        val quotient = absNum / absDen
        val remainder = absNum % absDen
        
        val roundedQuotient = if (remainder * 2L >= absDen) quotient + 1L else quotient
        return roundedQuotient * sign
    }

    private fun pow10(n: Int): Long {
        var result = 1L
        for (i in 0 until n) {
            result *= 10L
        }
        return result
    }
}
