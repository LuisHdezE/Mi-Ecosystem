# ADR-005 — Monetary Model

- Status: Proposed
- Date: 2026-09-29

## Context

Mi Ecosystem requires a robust monetary primitive to represent amounts across currencies like USD and CUP safely without arbitrary floating-point errors, as the platform must support financial domain operations like pricing, inventory adjustments, and shift handovers in multiple currencies.

## Decision

We have established a Shared Core monetary foundation with the following principles:

1. **Amount Representation:** Monetary amounts are represented using a 64-bit integer (`Long`) storing the "minor units" of a currency. `Float` and `Double` are strictly forbidden for representing money to prevent IEEE-754 precision errors and non-deterministic math.
2. **Checked Arithmetic & Overflow Policy:** `Long` eliminates floating-point representation error but still requires explicit overflow protection. Safe math operations guarantee that any arithmetic overflow (addition, subtraction, multiplication during conversion) explicitly fails with an `ArithmeticException` rather than silently wrapping around, especially near `Long.MAX_VALUE` and `Long.MIN_VALUE`.
3. **Currency Representation:** A `Currency` value object represents a normalized currency definition consisting of its code and minor-unit scale. The currency code is a normalized 3-letter ASCII identifier. Extraneous Unicode letters are explicitly rejected. Monetary compatibility requires the complete currency definition; the same code with different `minorUnits` is NOT compatible.
4. **Scale Strategy:** The scale is explicitly configured per currency (e.g., 2 for USD, 0 for JPY). To prevent unpredictable runaway scaling during math, the `minorUnits` property must be within a safe supported bound (0 to 9 inclusive). Arbitrary currency configurations must provide their scale; no scale defaults are assumed magically.
5. **Rational Rate Normalization & Conversion Safety:** Exchange rates use numerator/denominator fractions which are automatically normalized using their greatest common divisor (GCD). During conversion, rational factors are reduced before multiplication where possible. This prevents avoidable intermediate overflow, ensuring that representable final results do not fail merely because naive multiplication would overflow. Genuinely unrepresentable `Long` results still fail explicitly. Furthermore, an `ExchangeRate` additionally requires different currency codes and must not be used to convert scale variants of the same code.
6. **Rounding Strategy:** During conversions, rounding is explicitly implemented using integer division with deterministic Half-Up semantics. The boundary values and exact midpoint rounding behaves safely across JVM and Native without triggering negative overflow bugs.
7. **Explicit Conversion:** Any currency conversion must be explicit via `ExchangeRate.convert(Money)`. No automatic implicit conversions exist in basic operations like `plus` or `minus`.
8. **Cross-Currency Arithmetic Prohibition:** Standard arithmetic operations (`+`, `-`) across different currencies immediately reject the operation, preventing accidental logic errors.

## Consequences

- The `Long` minor units approach prevents precision errors at the cost of bounding the absolute maximum representable amount. While sufficient for the initial MiVenta scope, future expansion involving excessively large values might necessitate a KMP-compatible `BigInteger` implementation.
- Support for an arbitrary number of currencies beyond CUP and USD is baked in without hardcoding an ISO-4217 table, but developers must configure scales responsibly without relying on implicit default behavior.
- Multiplatform compatibility is guaranteed by using standard Kotlin integer math, independent of external decimal libraries.
