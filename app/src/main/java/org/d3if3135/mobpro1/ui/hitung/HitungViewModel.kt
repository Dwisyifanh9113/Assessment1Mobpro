package org.d3if3135.mobpro1.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if3135.mobpro1.model.HasilNilai
import org.d3if3135.mobpro1.model.KategoriNilai

class HitungViewModel : ViewModel() {

    private val hasilNilai = MutableLiveData<HasilNilai?>()
    private val navigasi = MutableLiveData<KategoriNilai?>()

    fun hitungNilai(nilaiKehadiran: Float, nilaiTugas : Float, nilaiAsesmen : Float) {
        val nilaiAkhir= (nilaiKehadiran + nilaiTugas + nilaiAsesmen) / 3f
        val nilaiHuruf = getKategori(nilaiAkhir)
        hasilNilai.value = HasilNilai(nilaiAkhir, nilaiHuruf)
    }
    private fun getKategori(nilaiAkhir : Float): KategoriNilai {
        val kategori = if (nilaiAkhir >= 81 && nilaiAkhir <=100){
            KategoriNilai.A
        }else if (nilaiAkhir >= 61 && nilaiAkhir < 80){
            KategoriNilai.B
        }else if (nilaiAkhir > 41 && nilaiAkhir < 60){
            KategoriNilai.C
        }else if (nilaiAkhir > 21 && nilaiAkhir < 40){
            KategoriNilai.D
        }else KategoriNilai.E
        return kategori
    }
    fun getHasilNilai(): LiveData<HasilNilai?> = hasilNilai
    fun mulaiNavigasi() {
        navigasi.value = hasilNilai.value?.nilaiHuruf
    }
    fun selesaiNavigasi() {
        navigasi.value = null
    }
    fun getNavigasi() : LiveData<KategoriNilai?> = navigasi
}



