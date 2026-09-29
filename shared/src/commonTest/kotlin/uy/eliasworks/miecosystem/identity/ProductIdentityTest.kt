package uy.eliasworks.miecosystem.identity

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ProductIdentityTest {

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

    private val testTheme = ProductTheme("theme-1", "1.0", testColors)

    @Test
    fun testValidIdentityCreation() {
        val identity = ProductIdentity("prod-1", "My Product", testTheme)
        assertEquals("prod-1", identity.productId)
        assertEquals("My Product", identity.displayName)
        assertEquals(testTheme, identity.theme)
    }

    @Test
    fun testBlankProductIdThrowsException() {
        assertFailsWith<IllegalArgumentException> {
            ProductIdentity("  ", "My Product", testTheme)
        }
    }

    @Test
    fun testBlankDisplayNameThrowsException() {
        assertFailsWith<IllegalArgumentException> {
            ProductIdentity("prod-1", "", testTheme)
        }
    }
}
