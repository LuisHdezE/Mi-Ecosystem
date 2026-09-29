package uy.eliasworks.miecosystem

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EcosystemInfoTest {

    @Test
    fun platformMessageReturnsExpectedContent() {
        val info = EcosystemInfo()
        assertEquals(
            "Mi Ecosystem shared core is running",
            info.platformMessage()
        )
    }

    @Test
    fun platformMessageIsNotBlank() {
        val info = EcosystemInfo()
        assertTrue(info.platformMessage().isNotBlank())
    }
}
