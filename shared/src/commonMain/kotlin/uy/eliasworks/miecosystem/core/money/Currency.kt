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
        operator fun invoke(code: String, minorUnits: Int): Currency {
            require(code.isNotBlank()) { "Currency code must not be blank" }
            require(code.length == 3) { "Currency code must be exactly 3 characters" }
            require(code.all { it in 'a'..'z' || it in 'A'..'Z' }) { "Currency code must contain only ASCII letters" }
            require(minorUnits in 0..9) { "Currency minor units must be between 0 and 9" }
            return Currency(code.uppercase(), minorUnits)
        }

        val USD = Currency("USD", 2)
        val CUP = Currency("CUP", 2)
    }
}
