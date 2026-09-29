package uy.eliasworks.miecosystem.identity

data class ProductIdentity(
    val productId: String,
    val displayName: String,
    val theme: ProductTheme
) {
    init {
        require(productId.isNotBlank()) { "Product ID cannot be blank" }
        require(displayName.isNotBlank()) { "Display name cannot be blank" }
    }
}
