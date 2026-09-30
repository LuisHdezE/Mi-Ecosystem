package uy.eliasworks.miecosystem.persistence.probe

interface PersistenceProbeRepository {
    suspend fun save(id: Long, value: String)
    suspend fun get(id: Long): PersistenceProbe?
    suspend fun getAll(): List<PersistenceProbe>
    suspend fun delete(id: Long)
}
