package uy.eliasworks.miecosystem.identity

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ProductThemeTest {

    private val testColors = SemanticColors(
        primary = ColorToken(0xFF000000),
        onPrimary = ColorToken(0xFFFFFFFF),
        secondary = ColorToken(0xFF111111),
        onSecondary = ColorToken(0xFFEEEEEE),
        background = ColorToken(0xFF222222),
        onBackground = ColorToken(0xFFDDDDDD),
        surface = ColorToken(0xFF333333),
        onSurface = ColorToken(0xFFCCCCCC),
        error = ColorToken(0xFF444444),
        onError = ColorToken(0xFFBBBBBB)
    )

    @Test
    fun testValidThemeCreation() {
        val theme = ProductTheme("theme-1", "1.0.0", testColors)
        assertEquals("theme-1", theme.id)
        assertEquals("1.0.0", theme.version)
        assertEquals(testColors, theme.colors)
    }

    @Test
    fun testBlankThemeIdThrowsException() {
        assertFailsWith<IllegalArgumentException> {
            ProductTheme("  ", "1.0.0", testColors)
        }
    }

    @Test
    fun testBlankThemeVersionThrowsException() {
        assertFailsWith<IllegalArgumentException> {
            ProductTheme("theme-1", "", testColors)
        }
    }
}
