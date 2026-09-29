# Mi Ecosystem

Mi Ecosystem is a modular Kotlin Multiplatform platform for building reusable business applications for Android and iOS.

## Vision

The project is designed around one principle:

> Product = Shared Core + Business Modules + Vertical Modules + Theme + Platform Adapters

The goal is not to copy one application into another. The goal is to build a reusable mobile product platform where domain logic, shared capabilities and visual foundations can evolve independently and be versioned.

## Architecture

- **Shared KMP**: Domain logic, application contracts, and platform-neutral business rules compiled for Android and iOS.
- **Android presentation**: Jetpack Compose + Material 3 (native).
- **iOS presentation**: SwiftUI (native).

> Share the brain when behavior should be identical; keep the face native when platform experience should remain native.

Compose Multiplatform UI is not part of this baseline. See [ADR-001](docs/adr/ADR-001-kmp-mobile-platform.md).

## Technical Reference Lab

[KMP-Zero-Cost-Lab](https://github.com/LuisHdezE/KMP-Zero-Cost-Lab) is the Technical Reference Lab for the KMP foundation. It validates technical hypotheses; Mi Ecosystem consumes proven patterns rather than depending on the lab as production code.

## Phase 1

The first delivery phase will validate the architecture through one functional product: **MiTaller**. See [PHASE-1.md](docs/PHASE-1.md) for acceptance criteria.

## Current implementation status

**KMP-001 — Technical Bootstrap** (in progress)

This repository currently contains:

- ✅ Shared KMP module (`shared/`) with `commonMain`, `commonTest`, `androidMain`, `iosMain`
- ✅ Android app (`androidApp/`) with Jetpack Compose + Material 3
- ✅ iOS app (`iosApp/`) with SwiftUI consuming the KMP shared framework
- ✅ Common tests for shared code
- ✅ CI workflows for Android and iOS builds
- ✅ Centralized version catalog (`gradle/libs.versions.toml`)

**Not yet implemented:**

- MiTaller business logic
- Domain entities (Customers, Repair Orders, Payments, etc.)
- Room / local persistence
- Navigation
- Design system
- Dependency injection

## Initial technology direction

- Kotlin Multiplatform
- Jetpack Compose (Android) / SwiftUI (iOS)
- Clean Architecture
- Coroutines / Flow
- Local persistence (future)
- Dependency Injection (future)
- Automated testing
- CI from the first implementation baseline

## Provisional identifiers

The following identifiers are used for technical compilation purposes and **require confirmation before being considered stable contracts**:

- `applicationId`: `uy.eliasworks.miecosystem`
- `namespace`: `uy.eliasworks.miecosystem.*`
- `bundleIdentifier`: `uy.eliasworks.miecosystem`

## Repository status

KMP-001 is the technical bootstrap checkpoint. It demonstrates that the repository can host shared KMP logic consumed by native Android and iOS applications with reproducible builds and CI validation.
