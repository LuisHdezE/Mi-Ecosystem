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
        
        // Half-up rounding test
        // 100 * 2 / 3 = 66.666... -> 67
        val rate2 = ExchangeRate(Currency.USD, Currency.CUP, 2L, 3L)
        val converted2 = rate2.convert(money)
        assertEquals(67L, converted2.amountMinorUnits)
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
}
