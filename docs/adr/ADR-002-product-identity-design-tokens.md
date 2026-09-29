# ADR-002 — Product Identity and Design Tokens

- Status: Proposed
- Date: 2026-09-29

## Context

Mi Ecosystem is designed to host multiple products (e.g., MiTaller, MiVenta) which require unique visual identities. However, we want to share business logic and semantic identity configuration across Android and iOS without duplicating configuration or polluting the shared code with platform-specific UI concepts. 

The goal is to provide a way for the shared module to define the "what" (semantic tokens and product metadata) while leaving the "how" (actual rendering) to each native platform.

## Decision

We will implement a shared semantic identity architecture:

```text
Shared semantic identity
        │
        ├── product metadata
        └── semantic visual tokens
                  │
          ┌───────┴───────┐
          ▼               ▼
 Android mapping      iOS mapping
 Compose/Material3    SwiftUI
```

**Guiding principle:** Share visual meaning, not platform UI implementation.

Specifically:
- `shared` can define identity and semantic meaning (e.g., `primary`, `onPrimary`, `background`).
- Android will translate these shared tokens into a `Material 3 ColorScheme`.
- iOS will translate these shared tokens into `SwiftUI Color`s.
- Compose components are not shared with iOS.
- SwiftUI components are not shared with Android.
- Compose Multiplatform UI continues to be outside the baseline.
- Themes must evolve through explicit versions.

## Consequences

- The `shared` codebase remains free of `android.graphics.Color`, Compose `Color`, or Apple `UIColor`/`Color` types, keeping the domain logic fully platform-agnostic.
- Each application can map the pure data to native paradigms (Material 3 or SwiftUI) naturally.
- A change in the semantic theme version represents a change in the visual contract. A change in internal implementation on Compose or SwiftUI that respects the contract does not necessitate a new semantic theme version.
- Adding a new product requires defining a new `ProductIdentity` and `ProductTheme` in shared, and both native platforms will automatically inherit the semantic configuration if correctly wired.
