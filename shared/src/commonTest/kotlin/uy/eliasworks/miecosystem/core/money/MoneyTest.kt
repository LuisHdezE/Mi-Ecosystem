package uy.eliasworks.miecosystem.core.money

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals

class MoneyTest {

    @Test
    fun testCreationAndZeroPositiveNegative() {
        val zero = Money(0L, Currency.USD)
        assertTrue(zero.isZero)
        assertFalse(zero.isPositive)
        assertFalse(zero.isNegative)

        val positive = Money(1500L, Currency.USD)
        assertTrue(positive.isPositive)
        assertFalse(positive.isZero)

        val negative = Money(-500L, Currency.CUP)
        assertTrue(negative.isNegative)
        assertFalse(negative.isZero)
    }

    @Test
    fun testSameCurrencyAddition() {
        val a = Money(1000L, Currency.USD)
        val b = Money(500L, Currency.USD)
        val result = a + b
        assertEquals(1500L, result.amountMinorUnits)
        assertEquals(Currency.USD, result.currency)
    }

    @Test
    fun testSameCurrencySubtraction() {
        val a = Money(1000L, Currency.USD)
        val b = Money(300L, Currency.USD)
        val result = a - b
        assertEquals(700L, result.amountMinorUnits)
        assertEquals(Currency.USD, result.currency)
    }

    @Test
    fun testCrossCurrencyAdditionRejected() {
        val usd = Money(1000L, Currency.USD)
        val cup = Money(500L, Currency.CUP)
        assertFailsWith<IllegalArgumentException> {
            usd + cup
        }
    }

    @Test
    fun testCrossCurrencySubtractionRejected() {
        val usd = Money(1000L, Currency.USD)
        val cup = Money(500L, Currency.CUP)
        assertFailsWith<IllegalArgumentException> {
            usd - cup
        }
    }

    @Test
    fun testEqualityIncludesCurrency() {
        val usd = Money(1000L, Currency.USD)
        val cup = Money(1000L, Currency.CUP)
        
        assertEquals(Money(1000L, Currency.USD), usd)
        assertNotEquals(usd as Any, cup as Any)
    }
}
