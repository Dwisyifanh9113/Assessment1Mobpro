package org.d3if3135.mobpro1.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.d3if3135.mobpro1.model.HasilNilai

@Entity(tableName = "nilai")
data class NilaiEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var nama: String,
    var kehadiran: Float,
    var tugas: Float,
    var asesmen: Float
)

