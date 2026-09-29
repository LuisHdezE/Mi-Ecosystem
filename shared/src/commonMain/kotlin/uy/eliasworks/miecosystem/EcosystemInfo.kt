package uy.eliasworks.miecosystem

/**
 * Technical bootstrap contract for verifying KMP shared code execution.
 *
 * This class exists solely to demonstrate that commonMain code is
 * reachable from both Android and iOS platform targets.
 * It is NOT a domain entity and will be replaced by real shared
 * contracts in subsequent checkpoints.
 */
class EcosystemInfo {
    fun platformMessage(): String =
        "Mi Ecosystem shared core is running"
}
