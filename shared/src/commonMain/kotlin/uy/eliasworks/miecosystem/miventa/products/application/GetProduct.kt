package uy.eliasworks.miecosystem.miventa.products.application

import uy.eliasworks.miecosystem.miventa.products.domain.Product
import uy.eliasworks.miecosystem.miventa.products.domain.ProductId

class GetProduct(private val repository: ProductRepository) {
    suspend operator fun invoke(id: ProductId): Product? {
        return repository.get(id)
    }
}
