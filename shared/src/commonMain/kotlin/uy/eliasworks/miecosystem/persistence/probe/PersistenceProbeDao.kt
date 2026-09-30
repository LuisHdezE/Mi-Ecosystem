package uy.eliasworks.miecosystem.persistence.probe

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query

@Dao
interface PersistenceProbeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(probe: PersistenceProbeEntity)

    @Query("SELECT * FROM persistence_probe WHERE id = :id")
    suspend fun getById(id: Long): PersistenceProbeEntity?

    @Query("SELECT * FROM persistence_probe")
    suspend fun getAll(): List<PersistenceProbeEntity>

    @Query("DELETE FROM persistence_probe WHERE id = :id")
    suspend fun deleteById(id: Long)
}
