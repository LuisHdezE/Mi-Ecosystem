# Phase 1 — MiTaller Validation

## Objective

Deliver one functional product, MiTaller, while proving that Mi Ecosystem is a reusable Android+iOS platform rather than a single application codebase.

## Functional scope

The first functional slice should cover:

- customers
- devices
- repair orders
- diagnosis
- estimates / budgets
- repair status tracking
- basic payments and expenses
- search
- repair history

The product is offline-first in this phase.

## Architectural acceptance criteria

Phase 1 is not complete unless all of the following are true:

1. Shared domain and business logic execute on Android and iOS.
2. Generic business concepts remain outside the workshop vertical.
3. MiTaller-specific logic is isolated in the workshop vertical.
4. Visual identity is configurable through the shared design-system/theme model.
5. No reusable capability is duplicated inside the MiTaller application layer.
6. Shared modules have explicit version boundaries.
7. Persistence and platform integrations are accessed through abstractions suitable for KMP.
8. Automated tests protect critical domain rules.
9. CI validates the supported build/test matrix.

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

Phase 1 can be declared complete only when MiTaller is functionally usable and the reuse proof demonstrates both cross-product reuse and Android/iOS portability of the intended shared logic.
