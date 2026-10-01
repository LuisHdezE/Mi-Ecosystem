package uy.eliasworks.miecosystem.miventa.products.domain

import kotlin.jvm.JvmInline

@JvmInline
value class Sku(val value: String) {
    init {
        require(value.isNotBlank()) { "SKU cannot be blank" }
        require(value.all { it.isLetterOrDigit() || it == '-' || it == '_' }) {
            "SKU can only contain alphanumeric characters, hyphens, and underscores"
        }
    }
}
