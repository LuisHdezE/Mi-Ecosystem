package uy.eliasworks.miecosystem.core.money

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CurrencyTest {

    @Test
    fun testValidCurrency() {
        val usd = Currency("USD")
        assertEquals("USD", usd.code)
        assertEquals(2, usd.minorUnits)

        val cup = Currency("CUP", 2)
        assertEquals("CUP", cup.code)
    }

    @Test
    fun testLowercaseNormalizedToUppercase() {
        val eur = Currency("eur")
        assertEquals("EUR", eur.code)
    }

    @Test
    fun testBlankRejected() {
        assertFailsWith<IllegalArgumentException> {
            Currency("")
        }
        assertFailsWith<IllegalArgumentException> {
            Currency("   ")
        }
    }

    @Test
    fun testInvalidLengthsRejected() {
        assertFailsWith<IllegalArgumentException> {
            Currency("US")
        }
        assertFailsWith<IllegalArgumentException> {
            Currency("USDD")
        }
    }

    @Test
    fun testNumericOrSymbolRejected() {
        assertFailsWith<IllegalArgumentException> {
            Currency("12A")
        }
        assertFailsWith<IllegalArgumentException> {
            Currency("\$US")
        }
    }
}
