# Offline Licensing Policy

Mi Ecosystem products (like MiVenta) can be activated without requiring a permanent connection to a server.

## Offline-First Activation Flow

```text
Installation
      ↓
Installation Identity
      ↓
Request Code
      ↓
External License Generator
      ↓
Signed License
      ↓
Activation Code
      ↓
Local Verification
      ↓
Activated
```

## Cryptographic Security

A simple SHA hash is insufficient for authorization. The architecture employs an asymmetric digital signature mechanism:

- **PRIVATE KEY:** Exists exclusively in the external License Generator. The client app never contains the private key.
- **PUBLIC KEY:** Embedded within the MiVenta app to verify the signature.

The generator signs a license payload, and the app verifies it using the public key.
*(Note: A specific cryptographic algorithm will be chosen in a future ADR).*

## Installation Identity

The license is linked to a specific installation. Since rigid physical identifiers (like IMEI) may not be available or are restricted by OS privacy policies, the `InstallationIdentity` concept is abstracted. It may combine generated cryptographic IDs, allowed stable platform info, and `productId`. The definitive structure of this identity is deferred to a future ADR.

## License Generator Tool

The License Generator is **NOT** part of the MiVenta app distributed to end-users. It is an independent administrative tool belonging to the Mi Ecosystem administrative suite. The private key remains exclusively under the control of the system owner.
