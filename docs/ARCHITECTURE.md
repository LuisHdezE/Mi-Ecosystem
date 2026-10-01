# Mi Ecosystem v2 — Architecture Baseline

## 1. Architectural intent

Mi Ecosystem is a reusable mobile product platform targeting Android and iOS.

> Product = Shared Core + Business Modules + Vertical Modules + Product Identity + Platform Presentation

## 2. Technical reference lab

[KMP-Zero-Cost-Lab](https://github.com/LuisHdezE/KMP-Zero-Cost-Lab) is the Technical Reference Lab for the KMP foundation. It validates technical hypotheses; Mi Ecosystem consumes proven patterns rather than depending on the lab as production code.

Validated baseline inherited as evidence:
- shared KMP domain/application logic and Room/SQLite persistence;
- Android-native Jetpack Compose / Material 3 presentation;
- iOS-native SwiftUI presentation;
- CRUD and persistence on physical Android and iPhone;
- Android and iOS CI build paths.

Unproven lab items remain unproven here until independently accepted, notably real Room schema migration with preserved data on both platforms.

## 3. Sharing rule

Share the brain when behavior should be identical; keep the face native when platform experience should remain native.

Default presentation policy:
- Android: Jetpack Compose + Material 3.
- iOS: SwiftUI.
- Compose Multiplatform UI is not the default and requires a separate ADR if later adopted.

Platform-specific APIs remain behind explicit adapters.

## 4. Target repository shape

```text
mi-ecosystem/
├── shared/
│   ├── core/
│   │   └── money/
│   ├── persistence/ (database, schemas, migrations, Room adapters/repositories)
│   └── business/
├── features/
│   ├── customers/
│   ├── catalog/
│   ├── inventory/
│   ├── sales/
│   ├── payments/
│   └── expenses/
├── verticals/
│   ├── miventa/
│   │   └── products/
│   └── workshop/
├── androidApp/
└── iosApp/
```

## 5. Domain boundaries

Generic concepts such as Customer, Money, Payment, Product, Expense, PhoneNumber, Address and Currency must not belong to the workshop vertical.

Workshop owns only workshop-specific concepts such as RepairOrder, Device, Diagnosis, RepairStatus, SparePartUsage, Warranty and TechnicianNote.

## 6. Phase 1 product

MiVenta is the first product. Phase 1 must prove reuse across products and portability of the intended shared logic across Android and iOS.
