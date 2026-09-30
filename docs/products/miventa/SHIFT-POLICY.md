# Shift Policy

The main conceptual grouping for time-based operations in MiVenta is a **Shift**, not a rigidly constrained "Daily Close".

## Unlimited Shifts

A business can open and close any number of shifts during a single commercial date (`businessDate`).
- **NO** maximum limit of three shifts.
- **NO** fixed duration (e.g., eight hours).
- **NO** rigid numbering per day.

A shift records at minimum:
- `id`
- `businessDate`
- `openedAt`
- `closedAt`
- `responsible`
- `status`

## Shift Handover

When an employee begins a shift, they may receive merchandise and cash. When closing, they must reconcile what they received against the operations during their shift.

The handover reconciliation compares expected versus actual:
- **Inventory:** `Received + Inbound - Outbound = Expected Inventory` vs. `Counted Inventory`
- **Cash:** `Received + Income - Expenses = Expected Cash` vs. `Counted Cash`

Differences can be determined based on these comparisons.
Extraordinary handovers within the same day are fully supported. Closing a shift and opening another does **NOT** imply closing the commercial day.

## Daily Reports

A daily report is an aggregation of all shifts corresponding to a specific `businessDate`.
There is no "1 day = 1 close" architectural rule. The report aggregates sales, collections, purchases, payments, expenses, cash movements, consignments, shift differences, and totals per currency (without blindly mixing CUP and USD).
