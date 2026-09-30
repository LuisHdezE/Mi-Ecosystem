# Domain Capabilities

MiVenta tracks operations related to inventory, purchases, sales, and consignments.

## Multi-Currency

MiVenta supports multiple currencies natively from the domain, primarily CUP (Cuban Peso) and USD (US Dollar).

- A monetary amount must always retain its currency (e.g., `Money(amount = 25.00, currency = USD)`).
- **NEVER** sum amounts of different currencies directly.
- Currency conversions require an explicit `ExchangeRate`. The conversion rate must be preserved in historical records, and no single universal USD/CUP rate is assumed.

## Inventory Traceability

Inventory is not represented simply by a mutable `stock = N` field. It must be auditable via explicit inventory movements:

- `PURCHASE_RECEIPT`
- `SALE`
- `CONSIGNMENT_OUT`
- `CONSIGNMENT_RETURN`
- `CUSTOMER_RETURN`
- `SUPPLIER_RETURN`
- `ADJUSTMENT_POSITIVE`
- `ADJUSTMENT_NEGATIVE`

## Purchases & Accounts Payable

A purchase is distinct from a payment. A purchase can be:
- Fully paid
- Partially paid
- Pending (Accounts Payable to suppliers)

Partial payments must preserve the amount, currency, timestamp, and a reference to the corresponding obligation.

## Sales & Accounts Receivable

A sale can be:
- Fully collected
- Partially collected
- On credit (Accounts Receivable from clients/debtors)

The system tracks the original amount, payments received, remaining balance, currency, debtor, and payment history.
