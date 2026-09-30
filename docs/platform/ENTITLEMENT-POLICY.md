# Entitlement Policy

The Mi Ecosystem platform relies on a generalized Entitlement Architecture to manage commercial tiers, capabilities, and usage restrictions across all products.

## Core Distinction

The architecture explicitly separates the following conceptual responsibilities:

- **Entitlement:** What commercial/service tier applies to the current session or installation.
- **Capability:** What the application is authorized to do (feature/action authorization).
- **UsagePolicy:** Quantitative or conditional restrictions applied to capabilities (e.g., limits on commercial operations).
- **License:** One possible source of entitlements. A License MUST NOT be synonymous with Entitlement itself.

## Entitlement Tiers

The platform conceptually supports the following tiers, though individual products select which tiers they implement:

- `FREE`
- `PRO`
- `PREMIUM_SERVICE` (reserved for future services that may justify recurring or externally provided capabilities)

An installation without a license can receive `FREE` entitlements from the product policy. A valid signed license may grant `PRO` entitlement. Future online mechanisms may grant `PREMIUM_SERVICE`.

## Capability-Based Access

Access control is capability-based rather than tier-based. Business logic should not rely on scattered checks like `isPaid`, `hasLicense`, or `isPremium`. Instead, it should query authorization conceptually:
`entitlements.can(CREATE_SALE)`

## Usage Policy

Quantitative restrictions belong to the `UsagePolicy` abstraction rather than the entitlement tier itself. For example, a `FREE` tier combined with a `UsagePolicy` might enforce a `maxCommercialOperations = N` limit. This limit remains configurable by product policy.

## Monetization Boundary

**Architectural Rule:** Monetization belongs to product policy/configuration and platform entitlement services, not to the Shared Business Core.

Generic domain concepts such as `Money`, `Currency`, `Product`, `Inventory`, `Sale`, `Payment`, `Receivable`, and `Payable` MUST NOT contain any knowledge of `FREE`, `PRO`, `PREMIUM_SERVICE`, license state, trial state, or activation code.
