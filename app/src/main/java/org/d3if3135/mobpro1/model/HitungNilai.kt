package org.d3if3135.mobpro1.model

import org.d3if3135.mobpro1.db.NilaiEntity

fun NilaiEntity.hitungNilai(): HasilNilai {
    val nilaiAkhir= (kehadiran + tugas + asesmen) / 3f
    val nilaiHuruf = if (nilaiAkhir >= 81 && nilaiAkhir <=100){
        KategoriNilai.A
    }else if (nilaiAkhir >= 61 && nilaiAkhir < 80){
        KategoriNilai.B
    }else if (nilaiAkhir > 41 && nilaiAkhir < 60){
        KategoriNilai.C
    }else if (nilaiAkhir > 21 && nilaiAkhir < 40){
        KategoriNilai.D
    }else KategoriNilai.E
    return HasilNilai(nilaiAkhir, nilaiHuruf)
}