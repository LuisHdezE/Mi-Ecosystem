package uy.eliasworks.miecosystem.miventa.products.application

import uy.eliasworks.miecosystem.miventa.products.domain.Product

class ListProducts(private val repository: ProductRepository) {
    suspend operator fun invoke(): List<Product> {
        return repository.getAll()
    }
}
