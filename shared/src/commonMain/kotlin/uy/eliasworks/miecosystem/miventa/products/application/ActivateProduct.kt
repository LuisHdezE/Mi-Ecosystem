package uy.eliasworks.miecosystem.miventa.products.application

import uy.eliasworks.miecosystem.miventa.products.domain.ProductId

class ActivateProduct(private val repository: ProductRepository) {
    suspend operator fun invoke(id: ProductId) {
        val product = repository.get(id) ?: throw IllegalArgumentException("Product not found")
        product.activate()
        repository.save(product)
    }
}
