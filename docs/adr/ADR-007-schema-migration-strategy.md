# ADR-007 — Schema Migration Strategy

- Status: Proposed
- Date: 2026-10-01

## Context
Mi Ecosystem utilizes Room with a bundled SQLite driver for offline-first persistence on both Android and iOS. As the schema evolves, we must have a reliable, proven strategy for migrating databases without losing user data. KMP-004 Stage 2 proves this capability by establishing a v1 -> v2 schema migration.

## Decision

1. **Explicit Migrations Are Mandatory:** We require explicit `Migration` definitions for all database schema changes.
2. **Schema History is Immutable:** Previously generated schema export JSON files (e.g. `1.json`) are immutable evidence of past versions and must be preserved alongside new versions.
3. **v1 -> v2 Technical Migration:** The technical probe `PersistenceProbeEntity` is evolved to add `createdAtEpochMs`. This proves we can successfully migrate an existing schema.
4. **Deterministic Legacy Default:** For new columns added to existing rows, we use a deterministic legacy default value (e.g., `0L` for timestamps) rather than dynamic values like the current device time to guarantee consistent migration behavior.
5. **Migration Registration Architecture:** Migrations are registered in the shared database builder (`addMigrations(MIGRATION_1_2)`) to ensure that both iOS and Android apps run exactly the same migration logic.
6. **No Destructive Fallback:** We explicitly avoid `fallbackToDestructiveMigration`. Schema evolution must always be deliberate and non-destructive.
7. **Schema JSON Preservation:** Room 3 KSP plugin generates `.json` schema dumps. These will be tracked and committed to version control.
8. **Test Strategy:** Migration validation requires a real database test. We create an actual v1 database via raw SQLite connection, insert legacy data, close it, and then open the DB via Room AppDatabase v2 to prove data preservation.
9. **Evidence Levels:** Verification requires strict distinction between IMPLEMENTED, COMPILED, EXECUTED, and PHYSICAL VERIFIED.
10. **Android vs iOS Runtime Evidence:** For testing actual migration execution during development, the Android instrumented device test (`androidDeviceTest`) is sufficient for proving the database logic. The iOS compilation must pass, but iOS runtime evidence is not strictly required unless there's a specific iOS defect.
11. **Future Migration Chain Policy:** When the database reaches version N, upgrades from any supported historical version must have an explicit valid migration path to N. We do not need a giant migration framework yet; simple sequential or direct migrations are sufficient.

## Consequences
- We have a proven, safe approach for schema evolution that works identically on iOS and Android.
- Data preservation is explicitly verified through automated device tests.
- Schema evolution is properly documented via KSP Room schema JSON dumps.
