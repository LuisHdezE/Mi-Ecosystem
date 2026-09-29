package uy.eliasworks.miecosystem.identity

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ColorTokenTest {

    @Test
    fun testValidColorToken() {
        val token = ColorToken(0xFFFFFFFF)
        assertEquals(0xFFFFFFFF, token.argb)
    }

    @Test
    fun testInvalidColorTokenThrowsException() {
        assertFailsWith<IllegalArgumentException> {
            ColorToken(-1L)
        }
        assertFailsWith<IllegalArgumentException> {
            ColorToken(0x100000000L) // Out of ARGB range
        }
    }
}
