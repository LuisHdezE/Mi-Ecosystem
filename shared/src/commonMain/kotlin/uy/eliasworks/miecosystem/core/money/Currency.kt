package uy.eliasworks.miecosystem.core.money

class Currency private constructor(val code: String, val minorUnits: Int) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as Currency
        return code == other.code && minorUnits == other.minorUnits
    }

    override fun hashCode(): Int {
        var result = code.hashCode()
        result = 31 * result + minorUnits
        return result
    }

    override fun toString(): String = "Currency(code='$code', minorUnits=$minorUnits)"
    companion object {
        operator fun invoke(code: String, minorUnits: Int = 2): Currency {
            require(code.isNotBlank()) { "Currency code must not be blank" }
            require(code.length == 3) { "Currency code must be exactly 3 characters" }
            require(code.all { it.isLetter() }) { "Currency code must contain only letters" }
            require(minorUnits >= 0) { "Currency minor units must be non-negative" }
            return Currency(code.uppercase(), minorUnits)
        }

        val USD = Currency("USD", 2)
        val CUP = Currency("CUP", 2)
    }
}
