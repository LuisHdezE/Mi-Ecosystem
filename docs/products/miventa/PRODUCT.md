# MiVenta Product Definition

MiVenta is the first functional product built on top of the Mi Ecosystem platform. It is an offline-first application for small retail merchants (initially oriented towards the Cuban context) who buy merchandise (individually or wholesale) and sell it from their homes, small establishments, or stores.

## Commercial Model

MiVenta's product policy follows a commercial model progressing from a **FREE** entitlement to a **PRO** entitlement via offline signed activation:

```text
FREE
  ↓
offline signed activation
  ↓
PRO
```

The FREE entitlement will allow a configurable amount of commercial operations without requiring activation (the exact quantitative limit is defined by a `UsagePolicy`).
When the FREE usage limit is reached, the creation of new commercial operations will be restricted until PRO entitlement is achieved via activation.

**Data Ownership:** Commercial entitlement MUST NOT determine ownership or accessibility of existing user data. After a FREE usage limit is reached, the user must retain at minimum:
- READ EXISTING DATA
- VIEW HISTORY
- BACKUP
- EXPORT

Operations subject to commercial entitlement may be restricted, but Backup/Export must never be held hostage by entitlement state.

## Identity and Customization

MiVenta will consume the shared KMP core and platform capabilities but will define its own `ProductIdentity` and `ProductTheme`.

## Core Guidelines

- **Offline-First:** All main commercial operations must work without internet. The application does not strictly depend on a remote API, central server, or cloud authentication.
- **Product Separation:** MiVenta-specific logic should not pollute the generic Mi Ecosystem shared capabilities.
