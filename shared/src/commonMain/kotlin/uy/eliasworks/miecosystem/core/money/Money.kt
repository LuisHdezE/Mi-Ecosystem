package uy.eliasworks.miecosystem.core.money

data class Money(val amountMinorUnits: Long, val currency: Currency) {
    operator fun plus(other: Money): Money {
        require(this.currency == other.currency) { "Cross-currency arithmetic forbidden" }
        return Money(this.amountMinorUnits + other.amountMinorUnits, this.currency)
    }

    operator fun minus(other: Money): Money {
        require(this.currency == other.currency) { "Cross-currency arithmetic forbidden" }
        return Money(this.amountMinorUnits - other.amountMinorUnits, this.currency)
    }

    val isZero: Boolean get() = amountMinorUnits == 0L
    val isPositive: Boolean get() = amountMinorUnits > 0L
    val isNegative: Boolean get() = amountMinorUnits < 0L
}
