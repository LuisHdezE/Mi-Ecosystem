package uy.eliasworks.miecosystem.persistence.probe

interface PersistenceProbeRepository {
    suspend fun save(id: Long, value: String, createdAtEpochMs: Long = 0L)
    suspend fun get(id: Long): PersistenceProbe?
    suspend fun getAll(): List<PersistenceProbe>
    suspend fun delete(id: Long)
}
