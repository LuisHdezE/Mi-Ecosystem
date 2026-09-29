package uy.eliasworks.miecosystem.identity

data class ProductTheme(
    val id: String,
    val version: String,
    val colors: SemanticColors
) {
    init {
        require(id.isNotBlank()) { "Theme ID cannot be blank" }
        require(version.isNotBlank()) { "Theme version cannot be blank" }
    }
}
