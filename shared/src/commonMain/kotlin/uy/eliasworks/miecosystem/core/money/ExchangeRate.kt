package uy.eliasworks.miecosystem.core.money

class ExchangeRate private constructor(
    val fromCurrency: Currency,
    val toCurrency: Currency,
    val rateNumerator: Long,
    val rateDenominator: Long
) {
    companion object {
        operator fun invoke(fromCurrency: Currency, toCurrency: Currency, numerator: Long, denominator: Long = 1L): ExchangeRate {
            require(fromCurrency.code != toCurrency.code) { "ExchangeRate source and target must have different normalized currency codes" }
            require(numerator > 0L && denominator > 0L) { "Rate must be strictly positive" }
            val g = gcd(numerator, denominator)
            return ExchangeRate(fromCurrency, toCurrency, numerator / g, denominator / g)
        }

        private tailrec fun gcd(a: Long, b: Long): Long {
            return if (b == 0L) a else gcd(b, a % b)
        }
        
        private tailrec fun gcdULong(a: ULong, b: ULong): ULong {
            return if (b == 0uL) a else gcdULong(b, a % b)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as ExchangeRate
        return fromCurrency == other.fromCurrency &&
               toCurrency == other.toCurrency &&
               rateNumerator == other.rateNumerator &&
               rateDenominator == other.rateDenominator
    }

    override fun hashCode(): Int {
        var result = fromCurrency.hashCode()
        result = 31 * result + toCurrency.hashCode()
        result = 31 * result + rateNumerator.hashCode()
        result = 31 * result + rateDenominator.hashCode()
        return result
    }

    fun convert(money: Money): Money {
        require(money.currency == fromCurrency) { "Wrong source currency" }

        val scaleDiff = toCurrency.minorUnits - fromCurrency.minorUnits
        val totalNum = if (scaleDiff > 0) safeMultiply(rateNumerator, pow10(scaleDiff)) else rateNumerator
        val totalDen = if (scaleDiff < 0) safeMultiply(rateDenominator, pow10(-scaleDiff)) else rateDenominator

        val g = gcd(totalNum, totalDen)
        val finalNum = (totalNum / g).toULong()
        val finalDen = (totalDen / g).toULong()

        val isNegative = money.amountMinorUnits < 0L
        val amountAbs = if (money.amountMinorUnits == Long.MIN_VALUE) 9223372036854775808uL else kotlin.math.abs(money.amountMinorUnits).toULong()

        val amountGcd = gcdULong(amountAbs, finalDen)
        val reducedAmount = amountAbs / amountGcd
        val reducedDen = finalDen / amountGcd

        val q = reducedAmount / reducedDen
        val r = reducedAmount % reducedDen

        val term1 = safeMultiplyULong(q, finalNum)
        
        val rNum = safeMultiplyULong(r, finalNum)
        val quotient2 = rNum / reducedDen
        val remainder = rNum % reducedDen
        
        val roundedQuotient2 = if (remainder >= reducedDen - remainder) quotient2 + 1uL else quotient2
        
        val totalAbs = safeAddULong(term1, roundedQuotient2)

        if (isNegative) {
            if (totalAbs > 9223372036854775808uL) throw ArithmeticException("Long overflow during conversion")
            val result = if (totalAbs == 9223372036854775808uL) Long.MIN_VALUE else -(totalAbs.toLong())
            return Money(result, toCurrency)
        } else {
            if (totalAbs > Long.MAX_VALUE.toULong()) throw ArithmeticException("Long overflow during conversion")
            return Money(totalAbs.toLong(), toCurrency)
        }
    }

    private fun safeMultiply(a: Long, b: Long): Long {
        val result = a * b
        if (a != 0L && result / a != b) {
            throw ArithmeticException("Long overflow during multiplication")
        }
        return result
    }

    private fun safeMultiplyULong(a: ULong, b: ULong): ULong {
        if (a == 0uL || b == 0uL) return 0uL
        val result = a * b
        if (result / a != b) {
            throw ArithmeticException("Long overflow during conversion multiplication")
        }
        return result
    }

    private fun safeAddULong(a: ULong, b: ULong): ULong {
        val result = a + b
        if (result < a) throw ArithmeticException("Long overflow during conversion addition")
        return result
    }

    private fun pow10(n: Int): Long {
        var result = 1L
        for (i in 0 until n) {
            result *= 10L
        }
        return result
    }
}
