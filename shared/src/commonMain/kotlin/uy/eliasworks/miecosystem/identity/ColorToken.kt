package uy.eliasworks.miecosystem.identity

data class ColorToken(val argb: Long) {
    init {
        require(argb in 0..0xFFFFFFFFL) { "Invalid ARGB color value" }
    }
}
