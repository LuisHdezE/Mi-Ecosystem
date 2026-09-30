# ADR-004 — Offline Licensing Architecture

- Status: Proposed
- Date: 2026-09-29

## Context

Mi Ecosystem apps, starting with MiVenta, require an offline-first commercial model where the app can be used for a trial period and later activated. The activation process must work without requiring a permanent internet connection, as users in certain contexts may lack consistent connectivity. 

## Decision

We will implement an offline-first asymmetric cryptographic licensing system. 
- A central, independent **License Generator** (an administrative tool outside the app) will hold a **Private Key**.
- The client app (MiVenta) will embed the corresponding **Public Key**.
- When a user wants to activate the app, they provide their `InstallationIdentity`.
- The License Generator signs an activation payload containing the permissions and identity.
- The client app verifies this signed license payload using the public key and activates the application.

## Consequences

- The client application is never exposed to the private key, ensuring attackers cannot generate valid licenses locally.
- Activation can occur entirely offline if the activation payload is transmitted via SMS, phone call, or local file transfer.
- The specific cryptographic algorithm (e.g., Ed25519, RSA) and the composition of `InstallationIdentity` will be defined in future technical ADRs when implementation begins.
- A `TrialPolicy` will govern the free tier and restrict feature creation without altering data ownership (users will always retain access to their existing data).
