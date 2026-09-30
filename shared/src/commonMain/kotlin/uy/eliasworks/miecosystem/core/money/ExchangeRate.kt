package uy.eliasworks.miecosystem.core.money

class ExchangeRate private constructor(
    val fromCurrency: Currency,
    val toCurrency: Currency,
    val rateNumerator: Long,
    val rateDenominator: Long
) {
    companion object {
        operator fun invoke(fromCurrency: Currency, toCurrency: Currency, numerator: Long, denominator: Long = 1L): ExchangeRate {
            require(fromCurrency != toCurrency) { "Source and target currencies must differ" }
            require(numerator > 0L && denominator > 0L) { "Rate must be strictly positive" }
            val g = gcd(numerator, denominator)
            return ExchangeRate(fromCurrency, toCurrency, numerator / g, denominator / g)
        }

        private tailrec fun gcd(a: Long, b: Long): Long {
            return if (b == 0L) a else gcd(b, a % b)
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
        val finalNum = totalNum / g
        val finalDen = totalDen / g

        val numerator = safeMultiply(money.amountMinorUnits, finalNum)
        val result = divideHalfUp(numerator, finalDen)
        
        return Money(result, toCurrency)
    }

    private fun safeMultiply(a: Long, b: Long): Long {
        val result = a * b
        if (a != 0L && result / a != b) {
            throw ArithmeticException("Long overflow during multiplication")
        }
        return result
    }

    private fun divideHalfUp(num: Long, den: Long): Long {
        if (den == 0L) throw ArithmeticException("Division by zero")
        
        val isNegative = (num < 0L) xor (den < 0L)
        
        val uNum = if (num == Long.MIN_VALUE) 9223372036854775808uL else kotlin.math.abs(num).toULong()
        val uDen = if (den == Long.MIN_VALUE) 9223372036854775808uL else kotlin.math.abs(den).toULong()
        
        val quotient = uNum / uDen
        val remainder = uNum % uDen
        
        val roundedQuotient = if (remainder >= uDen - remainder) quotient + 1uL else quotient
        
        if (isNegative) {
            if (roundedQuotient > 9223372036854775808uL) throw ArithmeticException("Overflow during rounding")
            return if (roundedQuotient == 9223372036854775808uL) Long.MIN_VALUE else -(roundedQuotient.toLong())
        } else {
            if (roundedQuotient > Long.MAX_VALUE.toULong()) throw ArithmeticException("Overflow during rounding")
            return roundedQuotient.toLong()
        }
    }

    private fun pow10(n: Int): Long {
        var result = 1L
        for (i in 0 until n) {
            result *= 10L
        }
        return result
    }
}
