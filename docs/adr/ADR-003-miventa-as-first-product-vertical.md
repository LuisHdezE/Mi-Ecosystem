# ADR-003 — MiVenta as First Product Vertical

- Status: Proposed
- Date: 2026-09-29

## Context

Mi Ecosystem was initially envisioned with "MiTaller" as the primary pilot product to validate the architecture (as stated in early technical documentation). However, business priorities and early adopter demographics have identified a more immediate need for a general retail and consignment tracking application, especially tailored for the Cuban merchant context.

## Decision

We have decided to formalize **MiVenta** as the first functional product built on top of Mi Ecosystem. MiVenta will validate the platform's multi-currency, offline-first, and reusable capabilities before moving on to MiTaller.

The architecture will ensure that MiVenta logic remains isolated in its product configuration and does not pollute the Shared Core. Reusable commercial modules built for MiVenta must be abstract enough to be consumed by MiTaller in the future.

## Consequences

- All existing documentation referring to MiTaller as the first product will be updated.
- MiTaller remains a valid future product, but MiVenta takes priority.
- Core development will focus on multi-currency support, inventory traceability, consignments, and shift-based operations.
