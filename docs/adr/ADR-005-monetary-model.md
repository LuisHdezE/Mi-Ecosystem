# ADR-005 — Monetary Model

- Status: Proposed
- Date: 2026-09-29

## Context

Mi Ecosystem requires a robust monetary primitive to represent amounts across currencies like USD and CUP safely without arbitrary floating-point errors, as the platform must support financial domain operations like pricing, inventory adjustments, and shift handovers in multiple currencies.

## Decision

We have established a Shared Core monetary foundation with the following principles:

1. **Amount Representation:** Monetary amounts are represented using a 64-bit integer (`Long`) storing the "minor units" of a currency. `Float` and `Double` are strictly forbidden for representing money to prevent IEEE-754 precision errors and non-deterministic math.
2. **Currency Representation:** A `Currency` value object ensures immutability, normalization to uppercase, and valid 3-letter codes. It also encapsulates the currency's scale (`minorUnits`), decoupling the precision requirement from the `Money` object.
3. **Scale Strategy:** The scale (e.g., 2 decimal places for USD, 0 for JPY, 3 for BHD) is tracked by `Currency.minorUnits`, ensuring `Money` dynamically adjusts math based on the currency in question.
4. **Rounding Strategy:** During conversions, rounding is explicitly implemented using integer division with Half-Up deterministic semantics. This guarantees consistent financial rounding across JVM and Native without relying on platform-specific floating-point rounding models.
5. **Explicit Conversion:** Any currency conversion must be explicit via `ExchangeRate.convert(Money)`. No automatic implicit conversions exist in basic operations like `plus` or `minus`.
6. **Cross-Currency Arithmetic Prohibition:** Standard arithmetic operations (`+`, `-`) across different currencies immediately reject the operation, preventing accidental logic errors.

## Consequences

- The `Long` minor units approach prevents precision errors at the cost of bounding the absolute maximum representable amount. While sufficient for the initial MiVenta scope, future expansion involving excessively large values might necessitate a KMP-compatible `BigInteger` implementation.
- Support for an arbitrary number of currencies beyond CUP and USD is baked in without hardcoding an ISO-4217 table.
- Multiplatform compatibility is guaranteed by using standard Kotlin integer math, independent of external decimal libraries.
