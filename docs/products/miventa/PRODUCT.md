# MiVenta Product Definition

MiVenta is the first functional product built on top of the Mi Ecosystem platform. It is an offline-first application for small retail merchants (initially oriented towards the Cuban context) who buy merchandise (individually or wholesale) and sell it from their homes, small establishments, or stores.

## Commercial Model

MiVenta follows a **Free / Activated** model.
The free installation will allow a configurable amount of commercial operations without requiring a license (the exact limit is defined by a `TrialPolicy`).
When the trial limit is reached, the creation of new commercial operations will be restricted until an activation license is provided.

**Data Ownership:** The end of the trial period MUST NOT block the user from accessing their own data. Even without an active license, users can read existing information, view historical records, and perform backup/export actions.

## Identity and Customization

MiVenta will consume the shared KMP core and platform capabilities but will define its own `ProductIdentity` and `ProductTheme`.

## Core Guidelines

- **Offline-First:** All main commercial operations must work without internet. The application does not strictly depend on a remote API, central server, or cloud authentication.
- **Product Separation:** MiVenta-specific logic should not pollute the generic Mi Ecosystem shared capabilities.
