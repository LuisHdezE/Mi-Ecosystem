# Backup and Restore Policy

Backup and restore are mandatory platform capabilities.

## Abstract Backup Engine

The architecture completely separates the backup representation from the storage destination.
The core backup engine will generate/consume backups, which can then be routed by platform adapters to various destinations:
- Local file
- Share/Export intent
- Google Drive
- Future providers

The Shared Core does **NOT** depend on Google APIs, Gmail, or Google Drive natively. These integrations will be implemented as platform-specific adapters.

## Backup Format

The backup uses a portable, versioned logical representation (initially JSON).
A raw dump of Room tables is strictly forbidden as a public backup contract.

Conceptual envelope:
```text
BackupEnvelope
- formatVersion
- productId
- productVersion
- databaseSchemaVersion
- createdAt
- data
```

This structure ensures that future architectural evolutions can safely migrate backups between compatible versions. Restore processes must include validation before replacing any existing state.
