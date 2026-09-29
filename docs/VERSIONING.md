# Versioning Strategy

Mi Ecosystem must allow reusable logic and product identity to evolve independently.

## Versioned building blocks

The platform will treat the following as independently versionable boundaries:

- shared core
- reusable business features
- vertical modules
- design system
- product theme/configuration

Example:

```text
mi-core           1.0.0
mi-customers      1.1.0
mi-sales          1.3.0
mi-payments       1.2.0
mi-design-system  1.4.0
workshop          1.0.0
```

## Product composition

A product release records the versions of the modules that compose it.

```text
MiTaller 1.0.0
Core          1.0.0
Customers     1.1.0
Inventory     1.0.2
Sales         1.3.0
Payments      1.2.0
Workshop      1.0.0
DesignSystem  1.4.0
Theme         MiTaller
```

## Visual identity

The design system owns reusable components and visual primitives.

Each product supplies configurable tokens such as:

- product name
- logo
- primary and secondary colors
- background and surface colors
- typography
- shapes
- spacing
- icon choices where required

A product theme may change without copying business logic or shared UI components.

## Semantic Theme Versioning

The shared module defines the **Semantic Theme** (e.g., `primary`, `background`) and its version. This represents the visual contract.

This architecture explicitly separates three distinct areas:
```text
Shared semantic product identity
        ≠
Android UI implementation
        ≠
iOS UI implementation
```

A semantic theme version identifies the abstract visual contract. A change in the internal implementation of Android (Jetpack Compose) or iOS (SwiftUI) components that does not modify this shared contract does not necessarily obligate a change in the semantic theme version.

## Compatibility rule

Module upgrades must preserve explicit contracts or declare a breaking change. Semantic Versioning is the intended baseline for reusable modules once implementation begins.
