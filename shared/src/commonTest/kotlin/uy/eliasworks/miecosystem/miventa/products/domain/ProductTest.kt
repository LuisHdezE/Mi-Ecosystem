package uy.eliasworks.miecosystem.miventa.products.domain

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ProductTest {

    @Test
    fun validProductCreation() {
        val product = Product(
            id = ProductId("1"),
            name = "Valid Product",
            sku = Sku("SKU-123"),
            description = "A valid product"
        )
        assertEquals("Valid Product", product.name)
        assertEquals(ProductStatus.ACTIVE, product.status)
    }

    @Test
    fun blankNameRejected() {
        assertFailsWith<IllegalArgumentException> {
            Product(
                id = ProductId("1"),
                name = "   ",
                sku = Sku("SKU-123"),
                description = "Desc"
            )
        }
    }

    @Test
    fun renameRejectsBlankName() {
        val product = Product(ProductId("1"), "Name", Sku("SKU-1"), "Desc")
        assertFailsWith<IllegalArgumentException> {
            product.rename("  ")
        }
        product.rename("New Name")
        assertEquals("New Name", product.name)
    }

    @Test
    fun invalidSkuRejected() {
        assertFailsWith<IllegalArgumentException> {
            Sku("   ")
        }
        assertFailsWith<IllegalArgumentException> {
            Sku("INVALID SKU#") // Contains hash
        }
    }

    @Test
    fun activateDeactivate() {
        val product = Product(ProductId("1"), "Name", Sku("SKU-1"), "Desc")
        
        // Starts active
        assertFailsWith<IllegalStateException> {
            product.activate()
        }
        
        product.deactivate()
        assertEquals(ProductStatus.INACTIVE, product.status)

        assertFailsWith<IllegalStateException> {
            product.deactivate()
        }

        product.activate()
        assertEquals(ProductStatus.ACTIVE, product.status)
    }
}
