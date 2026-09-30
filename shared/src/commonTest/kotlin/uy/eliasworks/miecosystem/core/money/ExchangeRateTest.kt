package uy.eliasworks.miecosystem.core.money

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ExchangeRateTest {

    @Test
    fun testValidRateCreation() {
        val rate = ExchangeRate(Currency.USD, Currency.CUP, 24L)
        assertEquals(Currency.USD, rate.fromCurrency)
        assertEquals(Currency.CUP, rate.toCurrency)
        assertEquals(24L, rate.rateNumerator)
        assertEquals(1L, rate.rateDenominator)
    }

    @Test
    fun testZeroOrNegativeRejected() {
        assertFailsWith<IllegalArgumentException> {
            ExchangeRate(Currency.USD, Currency.CUP, 0L)
        }
        assertFailsWith<IllegalArgumentException> {
            ExchangeRate(Currency.USD, Currency.CUP, -5L)
        }
    }

    @Test
    fun testSameCurrencyRejected() {
        assertFailsWith<IllegalArgumentException> {
            ExchangeRate(Currency.USD, Currency.USD, 1L)
        }
    }

    @Test
    fun testCorrectDirectionalConversion() {
        val rate = ExchangeRate(Currency.USD, Currency.CUP, 50L)
        val original = Money(1000L, Currency.USD) // 10.00 USD
        val converted = rate.convert(original)
        
        assertEquals(50000L, converted.amountMinorUnits) // 500.00 CUP
        assertEquals(Currency.CUP, converted.currency)
    }

    @Test
    fun testWrongSourceCurrencyRejected() {
        val rate = ExchangeRate(Currency.USD, Currency.CUP, 50L)
        val cup = Money(500L, Currency.CUP)
        
        assertFailsWith<IllegalArgumentException> {
            rate.convert(cup)
        }
    }

    @Test
    fun testDeterministicRounding() {
        val rate = ExchangeRate(Currency.USD, Currency.CUP, 1L, 3L)
        val money = Money(100L, Currency.USD) // 1.00 USD
        val converted = rate.convert(money)
        
        // 100 * 1 / 3 = 33.333... -> 33
        assertEquals(33L, converted.amountMinorUnits)
        
        // Half-up rounding test positive midpoint
        // 100 * 1 / 2 = 50 -> if we had a case that gave .5
        val rateHalf = ExchangeRate(Currency.USD, Currency.CUP, 1L, 200L) // 100 / 200 = 0.5 -> 1
        val convertedHalf = rateHalf.convert(money)
        assertEquals(1L, convertedHalf.amountMinorUnits)
        
        // negative midpoint: -100 * 1 / 200 = -0.5 -> -1
        val negMoney = Money(-100L, Currency.USD)
        val convertedNegHalf = rateHalf.convert(negMoney)
        assertEquals(-1L, convertedNegHalf.amountMinorUnits)
    }

    @Test
    fun testOriginalMoneyUnchanged() {
        val rate = ExchangeRate(Currency.USD, Currency.CUP, 5L)
        val original = Money(1000L, Currency.USD)
        val converted = rate.convert(original)
        
        assertEquals(1000L, original.amountMinorUnits)
        assertEquals(Currency.USD, original.currency)
        assertEquals(5000L, converted.amountMinorUnits)
        assertEquals(Currency.CUP, converted.currency)
    }
    
    @Test
    fun testConversionWithDifferentScaleCurrencies() {
        val bhd = Currency("BHD", 3)
        // Rate: 1 USD = 0.376 BHD => numerator 376, denominator 1000
        val rate = ExchangeRate(Currency.USD, bhd, 376L, 1000L)
        
        val money = Money(1000L, Currency.USD) // 10.00 USD
        val converted = rate.convert(money)
        // Expected: 10 * 0.376 = 3.760 BHD = 3760 minor units
        assertEquals(3760L, converted.amountMinorUnits)
        assertEquals(bhd, converted.currency)
    }

    @Test
    fun testConversionMultiplicationOverflowProtected() {
        val maxMoney = Money(Long.MAX_VALUE, Currency.USD)
        val rate = ExchangeRate(Currency.USD, Currency.CUP, 5L)
        assertFailsWith<ArithmeticException> {
            rate.convert(maxMoney)
        }
    }

    @Test
    fun testLongMinValueRoundingProtected() {
        // If money value is highly negative and rate causes it to reach MIN_VALUE boundary
        val rate = ExchangeRate(Currency.USD, Currency.CUP, 1L, 1L)
        val minMoney = Money(Long.MIN_VALUE, Currency.USD)
        val converted = rate.convert(minMoney)
        assertEquals(Long.MIN_VALUE, converted.amountMinorUnits)
    }

    @Test
    fun testEquivalentRationalExchangeRatesAndEquality() {
        val rate1 = ExchangeRate(Currency.USD, Currency.CUP, 1L, 2L)
        val rate2 = ExchangeRate(Currency.USD, Currency.CUP, 50L, 100L)
        
        assertEquals(rate1, rate2)
        assertEquals(rate1.hashCode(), rate2.hashCode())
        
        // internally it should be normalized to 1/2
        assertEquals(1L, rate2.rateNumerator)
        assertEquals(2L, rate2.rateDenominator)
    }
}
