package uy.eliasworks.miecosystem.miventa.products.domain

class Product(
    val id: ProductId,
    name: String,
    sku: Sku,
    description: String,
    status: ProductStatus = ProductStatus.ACTIVE
) {
    var name: String = name
        private set
    var sku: Sku = sku
        private set
    var description: String = description
        private set
    var status: ProductStatus = status
        private set

    init {
        require(this.name.isNotBlank()) { "Product name cannot be blank" }
    }

    fun rename(newName: String) {
        require(newName.isNotBlank()) { "Product name cannot be blank" }
        this.name = newName
    }

    fun changeDescription(newDescription: String) {
        this.description = newDescription
    }

    fun changeSku(newSku: Sku) {
        this.sku = newSku
    }

    fun activate() {
        check(status == ProductStatus.INACTIVE) { "Product is already active" }
        this.status = ProductStatus.ACTIVE
    }

    fun deactivate() {
        check(status == ProductStatus.ACTIVE) { "Product is already inactive" }
        this.status = ProductStatus.INACTIVE
    }
}
