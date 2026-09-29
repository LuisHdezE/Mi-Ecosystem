# ADR-001 — KMP shared brain with native platform presentation

- Status: Proposed
- Date: 2026-09-29
- Technical reference: [KMP-Zero-Cost-Lab](https://github.com/LuisHdezE/KMP-Zero-Cost-Lab)

## Context

Mi Ecosystem targets Android and iOS and needs shared business semantics without erasing platform-native user experience.

KMP-Zero-Cost-Lab has already validated shared KMP domain/controller/persistence, Room/SQLite on both targets, native Compose on Android, native SwiftUI on iOS, physical-device CRUD/persistence, and CI build paths.

## Decision

Use Kotlin Multiplatform for the shared brain: domain, application logic, repository contracts, reusable business rules and persistence where validated.

Use native presentation by default:
- Android: Jetpack Compose + Material 3.
- iOS: SwiftUI.

Use target source sets and explicit adapters for platform-specific composition and APIs.

Compose Multiplatform UI is not part of this baseline. Adopting it later requires evidence and a separate ADR.

KMP-Zero-Cost-Lab is a reference laboratory, not a runtime/build dependency of Mi Ecosystem.

## Consequences

- Business behavior has one authoritative implementation.
- Android and iOS preserve native UX conventions.
- Proven lab patterns reduce speculative architecture.
- Platform presentation code will intentionally differ.
- Interop and Apple build/release tooling remain explicit engineering concerns.

## Evidence policy

A capability proven in the lab may be adopted as a reference pattern, but Mi Ecosystem still needs its own automated verification.

Known lab gaps, including real Room schema migration with data preservation on both platforms, must not be described as solved until Mi Ecosystem has evidence.

## Guardrail

Share behavior because it must be identical, not merely because it can compile on both platforms.
