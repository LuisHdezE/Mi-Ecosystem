package uy.eliasworks.miecosystem.core.money

data class Money(val amountMinorUnits: Long, val currency: Currency) {
    operator fun plus(other: Money): Money {
        require(this.currency == other.currency) { "Cross-currency arithmetic forbidden" }
        val result = this.amountMinorUnits + other.amountMinorUnits
        if ((this.amountMinorUnits xor result) and (other.amountMinorUnits xor result) < 0L) {
            throw ArithmeticException("Long overflow during addition")
        }
        return Money(result, this.currency)
    }

    operator fun minus(other: Money): Money {
        require(this.currency == other.currency) { "Cross-currency arithmetic forbidden" }
        val result = this.amountMinorUnits - other.amountMinorUnits
        if ((this.amountMinorUnits xor other.amountMinorUnits) and (this.amountMinorUnits xor result) < 0L) {
            throw ArithmeticException("Long overflow during subtraction")
        }
        return Money(result, this.currency)
    }

    val isZero: Boolean get() = amountMinorUnits == 0L
    val isPositive: Boolean get() = amountMinorUnits > 0L
    val isNegative: Boolean get() = amountMinorUnits < 0L
}
