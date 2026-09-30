package uy.eliasworks.miecosystem.persistence.probe.room

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "persistence_probe")
data class PersistenceProbeEntity(
    @PrimaryKey
    val id: Long,
    val value: String
)
