# MIVENTA-001 — Products Domain

## Scope
This specification defines the Product domain foundational layer for the MiVenta vertical application. The layer encompasses the domain models and application use case boundaries for product lifecycle management. 

## Product Ownership
Products belong strictly to the MiVenta application. The concepts have not been generalized or extracted into Shared Core as they have not yet been demonstrated to be broadly cross-product.

## Invariants
- `ProductId`: Must be a non-blank strongly-typed string.
- `Sku`: Must be non-blank and conform to alphanumeric characters, dashes, and underscores.
- `Product Name`: Must be non-blank.
- `Product Lifecycle`: State transitions enforce valid flows (e.g., cannot activate an already active product).

## Lifecycle
- **Create**: Product is instantiated with ID, Name, SKU, and Description. State is ACTIVE by default.
- **Rename**: Updates the product's name (must be valid/non-blank).
- **Change Description**: Updates the description text.
- **Activate**: Transitions product to ACTIVE state.
- **Deactivate**: Transitions product to INACTIVE state.
- *Hard deletion is intentionally excluded.*

## SKU Uniqueness Rule
SKU uniqueness is a business requirement. The in-memory domain objects cannot guarantee global uniqueness. Protection against duplicate SKUs is managed strictly at the Application Use Case boundary via the repository contract (`ProductRepository.getBySku(sku)`). Note: in a distributed environment or multi-threaded runtime, this atomic uniqueness rule will eventually require transactional persistence constraints. That is outside the scope of this checkpoint.

## Application Operations
The Persistence-agnostic `ProductRepository` contract and Application Use Cases encompass:
- `CreateProduct`: Verifies SKU uniqueness and persists a new `Product`.
- `GetProduct`: Retrieves a single `Product`.
- `ListProducts`: Retrieves all `Products`.
- `UpdateProduct`: Evaluates SKU collisions and saves mutations.
- `ActivateProduct`: Executes domain state transition and saves.
- `DeactivateProduct`: Executes domain state transition and saves.

## Explicit Exclusions
The following concepts are explicitly forbidden in this domain representation:
- Room Entities / DAO / Schema configuration
- Platform UI (SwiftUI, Compose)
- Networking or remote sync logic
- Stock quantity, purchase prices, suppliers, sales, and consignments
