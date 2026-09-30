# Consignment Policy

Consignment is **NOT** equivalent to a sale. It is an independent commercial concept tracking merchandise handed over to a consignee (salesperson/vendor) to be sold.

## Consignment Lifecycle

The system must track the following per consignee:
- Merchandise delivered
- Quantities delivered
- Date of delivery
- Agreed commercial condition/price
- Quantities settled (sold and paid for)
- Money settled
- Merchandise returned
- Merchandise still in the possession of the consignee
- Pending balance

## Partial Settlements & Returns

The architecture fully supports:
- Partial settlements (e.g., settling a portion of the consigned merchandise).
- Partial or complete returns.

### Return Implications

A return must produce a corresponding inventory movement to bring the merchandise back into the business's stock.
- **NEVER** overwrite, delete, or rewrite historical delivery records to represent a return. Returns must be appended as explicitly new events or movements reflecting the return transaction.
