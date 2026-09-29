# Mi Ecosystem v2 — Architecture Baseline

## 1. Architectural intent

Mi Ecosystem is a reusable mobile product platform targeting Android and iOS.

Its defining equation is:

> Product = Shared Core + Business Modules + Vertical Modules + Theme + Platform Adapters

The architecture must prevent product-specific code from swallowing reusable business capabilities.

## 2. Initial repository shape

```text
mi-ecosystem/
├── platform/
│   ├── core/
│   ├── design-system/
│   └── testing/
├── features/
│   ├── customers/
│   ├── catalog/
│   ├── inventory/
│   ├── sales/
│   ├── payments/
│   └── expenses/
├── verticals/
│   └── workshop/
└── apps/
    └── mitaller/
```

## 3. Sharing rule

Share code when the capability is business- or platform-generic and stable enough to have a reusable contract.

Keep platform-specific code behind adapters whenever Android and iOS require different implementations.

Do not force shared UI or platform APIs when a native implementation is materially better.

## 4. Domain boundaries

Generic concepts such as Customer, Money, Payment, Product, Expense, PhoneNumber, Address and Currency must not belong to the workshop vertical.

The workshop vertical contains only workshop-specific concepts such as RepairOrder, Device, Diagnosis, RepairStatus, SparePartUsage, Warranty and TechnicianNote.

## 5. Phase 1 product

MiTaller is the first product used to validate this architecture.

The purpose of MiTaller Phase 1 is not merely to ship an app. It is to prove that the shared platform is reusable across products and across Android and iOS.
