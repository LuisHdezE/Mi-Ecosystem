package uy.eliasworks.miecosystem.miventa.products.application

import kotlinx.coroutines.runBlocking
import uy.eliasworks.miecosystem.miventa.products.domain.Product
import uy.eliasworks.miecosystem.miventa.products.domain.ProductId
import uy.eliasworks.miecosystem.miventa.products.domain.Sku
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class InMemoryProductRepository : ProductRepository {
    private val products = mutableMapOf<ProductId, Product>()

    override suspend fun save(product: Product) {
        products[product.id] = product
    }

    override suspend fun get(id: ProductId): Product? {
        return products[id]
    }

    override suspend fun getBySku(sku: Sku): Product? {
        return products.values.firstOrNull { it.sku == sku }
    }

    override suspend fun getAll(): List<Product> {
        return products.values.toList()
    }
}

class ProductUseCasesTest {

    @Test
    fun createProductSuccess() = runBlocking<Unit> {
        val repo = InMemoryProductRepository()
        val createProduct = CreateProduct(repo)

        val product = createProduct(
            id = ProductId("1"),
            name = "Product 1",
            sku = Sku("SKU-1"),
            description = "Desc"
        )
        assertEquals("Product 1", product.name)
        assertEquals(1, repo.getAll().size)
    }

    @Test
    fun createProductDuplicateSkuRejected() = runBlocking<Unit> {
        val repo = InMemoryProductRepository()
        val createProduct = CreateProduct(repo)

        createProduct(ProductId("1"), "Product 1", Sku("SKU-1"), "Desc")

        assertFailsWith<IllegalArgumentException> {
            createProduct(ProductId("2"), "Product 2", Sku("SKU-1"), "Desc 2")
        }
    }

    @Test
    fun updateProductDuplicateSkuRejected() = runBlocking<Unit> {
        val repo = InMemoryProductRepository()
        val createProduct = CreateProduct(repo)
        val updateProduct = UpdateProduct(repo)

        createProduct(ProductId("1"), "Product 1", Sku("SKU-1"), "Desc")
        createProduct(ProductId("2"), "Product 2", Sku("SKU-2"), "Desc")

        assertFailsWith<IllegalArgumentException> {
            updateProduct(ProductId("2"), "Product 2", "Desc", Sku("SKU-1"))
        }
    }
    
    @Test
    fun updateProductSuccess() = runBlocking<Unit> {
        val repo = InMemoryProductRepository()
        val createProduct = CreateProduct(repo)
        val updateProduct = UpdateProduct(repo)
        val getProduct = GetProduct(repo)

        createProduct(ProductId("1"), "Product 1", Sku("SKU-1"), "Desc")
        updateProduct(ProductId("1"), "Product 1 Updated", "Desc Updated", Sku("SKU-1-MOD"))
        
        val product = getProduct(ProductId("1"))!!
        assertEquals("Product 1 Updated", product.name)
        assertEquals("Desc Updated", product.description)
        assertEquals(Sku("SKU-1-MOD"), product.sku)
    }
}
