# Mi Ecosystem

Mi Ecosystem is a modular Kotlin Multiplatform platform for building reusable business applications for Android and iOS.

## Vision

The project is designed around one principle:

> Product = Shared Core + Business Modules + Vertical Modules + Theme + Platform Adapters

The goal is not to copy one application into another. The goal is to build a reusable mobile product platform where domain logic, shared capabilities and visual foundations can evolve independently and be versioned.

## Phase 1

The first delivery phase will validate the architecture through one functional product: **MiTaller**.

MiTaller will prove that:
- shared business logic can be reused across Android and iOS;
- generic business capabilities live outside the workshop vertical;
- product identity can change without duplicating shared functionality;
- modules can evolve with explicit version boundaries;
- the platform remains usable offline-first.

## Initial technology direction

- Kotlin Multiplatform
- Compose Multiplatform where appropriate
- Clean Architecture
- Coroutines / Flow
- Local persistence
- Dependency Injection
- Automated testing
- CI from the first implementation baseline

## Repository status

This repository currently contains the architectural and product baseline for Mi Ecosystem v2. Production code will start only after the Phase 1 architecture and acceptance criteria are frozen.
