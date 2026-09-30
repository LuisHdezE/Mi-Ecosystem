# Phase 1 — MiVenta Validation

## Objective

Deliver one functional product, MiVenta, while proving that Mi Ecosystem is a reusable Android+iOS platform rather than a single application codebase.

## Functional scope

The first functional slice should cover:

- inventory
- purchases
- sales
- consignments
- shifts
- basic payments and expenses
- search

The product is offline-first in this phase.

## Architectural acceptance criteria

Phase 1 is not complete unless all of the following are true:

1. Shared domain and business logic execute on Android and iOS.
2. Generic business concepts remain outside the MiVenta vertical.
3. MiVenta-specific logic is isolated in the product vertical.
4. Visual identity is configurable through the shared design-system/theme model.
5. No reusable capability is duplicated inside the MiVenta application layer.
6. Shared modules have explicit version boundaries.
7. Persistence and platform integrations are accessed through abstractions suitable for KMP.
8. Automated tests protect critical domain rules.
9. CI validates the supported build/test matrix.
10. Android presentation is validated with Jetpack Compose / Material 3.
11. iOS presentation is validated with SwiftUI.
12. At least one persisted-data upgrade path is tested before Phase 1 exit; Room schema migration must preserve existing data on both platforms.
13. Evidence distinguishes capabilities inherited as reference from KMP-Zero-Cost-Lab from capabilities independently verified in Mi Ecosystem.

## Technical reference

KMP-Zero-Cost-Lab is the reference laboratory for KMP feasibility and zero-cost build experiments. Mi Ecosystem must not import it as a production dependency. Patterns are promoted only with explicit evidence and project-local verification.

## Reuse proof

A small proof application or secondary product configuration must reuse a meaningful subset of the platform with a different product identity.

Minimum proof:

```text
MiDemo
├── Shared Core
├── Customers
├── Catalog
├── Payments
└── Different Theme
```

The proof must compile without copying the shared modules.

## Exit gate

Phase 1 can be declared complete only when MiVenta is functionally usable and the reuse proof demonstrates both cross-product reuse and Android/iOS portability of the intended shared logic.
