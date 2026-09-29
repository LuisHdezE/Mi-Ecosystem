# ADR-001 — Kotlin Multiplatform as the shared mobile foundation

- Status: Proposed
- Date: 2026-09-29

## Context

Mi Ecosystem evolved from an Android-only concept into a reusable mobile platform targeting Android and iOS.

The platform needs to share business logic and reusable capabilities without sacrificing platform-specific implementations when Android and iOS differ materially.

## Decision

Use Kotlin Multiplatform as the foundation for shared domain and application logic.

Use Compose Multiplatform where it provides clear reuse value, while allowing native/platform-specific UI or adapters whenever that produces a better technical result.

Platform-specific concerns remain behind explicit abstractions.

## Consequences

### Positive

- One shared business core for Android and iOS.
- Reusable feature modules can evolve independently.
- Less duplication of domain rules and application logic.
- Product variants can share logic while keeping distinct visual identities.

### Trade-offs

- KMP introduces additional build and interoperability complexity.
- Not every API or UI concern should be shared.
- iOS integration and release tooling require explicit validation in CI and on Apple tooling.

## Guardrail

KMP is a reuse mechanism, not a mandate to share every line of code.
