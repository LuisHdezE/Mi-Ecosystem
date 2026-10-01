package uy.eliasworks.miecosystem.miventa.products.application

import uy.eliasworks.miecosystem.miventa.products.domain.ProductId
import uy.eliasworks.miecosystem.miventa.products.domain.Sku

class UpdateProduct(private val repository: ProductRepository) {
    suspend operator fun invoke(
        id: ProductId,
        newName: String,
        newDescription: String,
        newSku: Sku
    ) {
        val product = repository.get(id) ?: throw IllegalArgumentException("Product not found")

        if (product.sku != newSku) {
            val existing = repository.getBySku(newSku)
            if (existing != null && existing.id != id) {
                throw IllegalArgumentException("Product with SKU ${newSku.value} already exists")
            }
        }

        product.rename(newName)
        product.changeDescription(newDescription)
        product.changeSku(newSku)

        repository.save(product)
    }
}
