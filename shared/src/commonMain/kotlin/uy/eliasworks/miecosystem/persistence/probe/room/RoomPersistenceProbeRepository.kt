package uy.eliasworks.miecosystem.persistence.probe.room

import uy.eliasworks.miecosystem.persistence.probe.PersistenceProbe
import uy.eliasworks.miecosystem.persistence.probe.PersistenceProbeRepository

class RoomPersistenceProbeRepository(
    private val dao: PersistenceProbeDao
) : PersistenceProbeRepository {

    override suspend fun save(id: Long, value: String, createdAtEpochMs: Long) {
        dao.upsert(PersistenceProbeEntity(id = id, value = value, createdAtEpochMs = createdAtEpochMs))
    }

    override suspend fun get(id: Long): PersistenceProbe? {
        val entity = dao.getById(id) ?: return null
        return PersistenceProbe(id = entity.id, value = entity.value, createdAtEpochMs = entity.createdAtEpochMs)
    }

    override suspend fun getAll(): List<PersistenceProbe> {
        return dao.getAll().map { PersistenceProbe(id = it.id, value = it.value, createdAtEpochMs = it.createdAtEpochMs) }
    }

    override suspend fun delete(id: Long) {
        dao.deleteById(id)
    }
}
