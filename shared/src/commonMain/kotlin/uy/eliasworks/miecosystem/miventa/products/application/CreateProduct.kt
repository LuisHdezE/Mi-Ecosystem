package uy.eliasworks.miecosystem.miventa.products.application

import uy.eliasworks.miecosystem.miventa.products.domain.Product
import uy.eliasworks.miecosystem.miventa.products.domain.ProductId
import uy.eliasworks.miecosystem.miventa.products.domain.ProductStatus
import uy.eliasworks.miecosystem.miventa.products.domain.Sku

class CreateProduct(private val repository: ProductRepository) {
    suspend operator fun invoke(
        id: ProductId,
        name: String,
        sku: Sku,
        description: String,
        status: ProductStatus = ProductStatus.ACTIVE
    ): Product {
        if (repository.getBySku(sku) != null) {
            throw IllegalArgumentException("Product with SKU ${sku.value} already exists")
        }
        val product = Product(id, name, sku, description, status)
        repository.save(product)
        return product
    }
}
