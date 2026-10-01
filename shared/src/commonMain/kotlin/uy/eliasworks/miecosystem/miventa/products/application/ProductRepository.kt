package uy.eliasworks.miecosystem.miventa.products.application

import uy.eliasworks.miecosystem.miventa.products.domain.Product
import uy.eliasworks.miecosystem.miventa.products.domain.ProductId
import uy.eliasworks.miecosystem.miventa.products.domain.Sku

interface ProductRepository {
    suspend fun save(product: Product)
    suspend fun get(id: ProductId): Product?
    suspend fun getBySku(sku: Sku): Product?
    suspend fun getAll(): List<Product>
}
