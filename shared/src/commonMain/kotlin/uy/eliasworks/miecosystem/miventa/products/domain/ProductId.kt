package uy.eliasworks.miecosystem.miventa.products.domain

import kotlin.jvm.JvmInline

@JvmInline
value class ProductId(val value: String) {
    init {
        require(value.isNotBlank()) { "ProductId cannot be blank" }
    }
}
