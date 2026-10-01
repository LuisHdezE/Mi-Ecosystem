# ADR-006 — KMP Persistence with Room and SQLite Bundled

- Status: Proposed
- Date: 2026-09-30

## Context

Mi Ecosystem requires a shared, offline-first persistence solution. The database infrastructure must support cross-platform operations on Android and iOS while maintaining a single source of truth for the schema, DAO logic, and repositories.

## Decision

We establish the KMP persistence foundation using the stack validated by `KMP-Zero-Cost-Lab`:

- **Room 3** (`androidx.room3:room3-runtime`, `androidx.room3:room3-compiler`)
- **SQLite Bundled** (`androidx.sqlite:sqlite-bundled`)
- **KSP** (Kotlin Symbol Processing)

### Architecture Principles

1. **Shared Persistence Architecture:**
   Entities, DAOs, and the abstract `AppDatabase` are defined in `commonMain`.
   Business logic interacts exclusively with a **Repository Boundary** (e.g., `PersistenceProbeRepository`). The Room DAO is not exposed outside the persistence layer.

2. **Platform Database Builders:**
   Room instantiation is platform-specific due to filesystem differences.
   - **Android:** Uses `Room.databaseBuilder` with `Context.getDatabasePath()`.
   - **iOS:** Uses `Room.databaseBuilder` pointing to the platform application-support directory (`Library/Application Support/mi-ecosystem.db` inside the application sandbox), resolved via `NSSearchPathForDirectoriesInDomains(NSApplicationSupportDirectory, …)`. The directory is created if it does not exist; failure to resolve or create it is treated as a deterministic initialization error.
   Android-specific or iOS-specific types do not leak into `commonMain`.

3. **Schema Export:**
   Room schema export is strictly enabled and exported to `shared/schemas/`. These JSON schema files must be committed to Git. This acts as versioned evidence for future database migrations.

4. **Database Versioning & Migration Policy:**
   Stage 1 implements DB `version = 1`. Currently, we only persist a technical `PersistenceProbeEntity`.
   Destructive migrations are **NOT** our production strategy. Future KMP-004 stages will demonstrate `v1 -> v2` migrations and prove preservation of existing data.

5. **Reference-Lab Evidence vs Mi Ecosystem Evidence:**
   The laboratory proved the infrastructure works conceptually, but Mi Ecosystem maintains its own separate evidence. Mi Ecosystem includes an Android instrumented persistence test designed to verify disk persistence across database reopenings. Runtime execution evidence is tracked separately from compilation (IMPLEMENTED vs COMPILED vs EXECUTED vs PHYSICAL DEVICE VERIFIED).

6. **Repository Boundary:**
   `PersistenceProbeRepository` serves as a neutral contract. Room implementation details are strictly isolated behind `RoomPersistenceProbeRepository`, ensuring no platform or framework types (e.g. Dao or Room Entity) leak to the domain logic.

## Consequences

- The business vertical (e.g. MiVenta) can now rely on a robust database infrastructure for offline-first capabilities.
- The use of `sqlite-bundled` guarantees consistent SQLite behavior across Android and iOS, bypassing platform-specific OS limitations or discrepancies.
