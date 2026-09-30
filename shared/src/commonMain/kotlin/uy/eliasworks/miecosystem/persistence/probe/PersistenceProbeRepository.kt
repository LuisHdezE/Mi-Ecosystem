package uy.eliasworks.miecosystem.persistence.probe

class PersistenceProbeRepository(private val dao: PersistenceProbeDao) {
    suspend fun saveProbe(id: Long, value: String) {
        dao.upsert(PersistenceProbeEntity(id, value))
    }

    suspend fun getProbe(id: Long): PersistenceProbeEntity? {
        return dao.getById(id)
    }

    suspend fun getAllProbes(): List<PersistenceProbeEntity> {
        return dao.getAll()
    }

    suspend fun deleteProbe(id: Long) {
        dao.deleteById(id)
    }
}
