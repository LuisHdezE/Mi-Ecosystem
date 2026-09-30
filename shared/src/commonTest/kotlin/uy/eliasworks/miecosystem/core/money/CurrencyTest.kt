package uy.eliasworks.miecosystem.core.money

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CurrencyTest {

    @Test
    fun testValidCurrency() {
        val usd = Currency("USD", 2)
        assertEquals("USD", usd.code)
        assertEquals(2, usd.minorUnits)

        val cup = Currency("CUP", 2)
        assertEquals("CUP", cup.code)
    }

    @Test
    fun testLowercaseNormalizedToUppercase() {
        val eur = Currency("eur", 2)
        assertEquals("EUR", eur.code)
    }

    @Test
    fun testBlankRejected() {
        assertFailsWith<IllegalArgumentException> {
            Currency("", 2)
        }
        assertFailsWith<IllegalArgumentException> {
            Currency("   ", 2)
        }
    }

    @Test
    fun testInvalidLengthsRejected() {
        assertFailsWith<IllegalArgumentException> {
            Currency("US", 2)
        }
        assertFailsWith<IllegalArgumentException> {
            Currency("USDD", 2)
        }
    }

    @Test
    fun testNumericOrSymbolRejected() {
        assertFailsWith<IllegalArgumentException> {
            Currency("12A", 2)
        }
        assertFailsWith<IllegalArgumentException> {
            Currency("\$US", 2)
        }
    }

    @Test
    fun testUnicodeRejected() {
        assertFailsWith<IllegalArgumentException> {
            Currency("ÉUR", 2)
        }
        assertFailsWith<IllegalArgumentException> {
            Currency("１２A", 2)
        }
    }

    @Test
    fun testScaleBounds() {
        val crypto = Currency("BTC", 8)
        assertEquals(8, crypto.minorUnits)

        assertFailsWith<IllegalArgumentException> {
            Currency("USD", -1)
        }
        assertFailsWith<IllegalArgumentException> {
            Currency("USD", 10) // > 9
        }
    }
}
