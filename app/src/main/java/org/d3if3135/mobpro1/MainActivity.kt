package org.d3if3135.mobpro1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.d3if3135.mobpro1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var namaMahasiswa : com.google.android.material.textfield.TextInputEditText
    lateinit var nilaiKehadiran : com.google.android.material.textfield.TextInputEditText
    lateinit var nilaiTugas : com.google.android.material.textfield.TextInputEditText
    lateinit var nilaiAsesmen : com.google.android.material.textfield.TextInputEditText
    lateinit var btnHitung : Button
    lateinit var btnReset : Button
    lateinit var nilaiAkhir : TextView
    lateinit var nilaiHuruf : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        namaMahasiswa = binding.namaInp
        nilaiKehadiran = binding.kehadiranInp
        nilaiTugas = binding.tugasInp
        nilaiAsesmen = binding.asesmenInp
        btnHitung = binding.buttonHitung
        btnReset = binding.buttonReset
        nilaiAkhir = binding.nilaiAkhir
        nilaiHuruf = binding.nilaiHuruf

        btnHitung.setOnClickListener {
            hitungNilai(
                namaMahasiswa.text.toString(),
                nilaiKehadiran.text.toString(),
                nilaiTugas.text.toString(),
                nilaiAsesmen.text.toString(),
            )
        }

        tampilkanButton(false)

        btnReset.setOnClickListener { resetHasil() }

    }

    private fun tampilkanButton(btn: Boolean) {
        if(!btn) {
            btnReset.visibility = View.INVISIBLE
            nilaiAkhir.visibility = View.INVISIBLE
            nilaiHuruf.visibility = View.INVISIBLE
        }else {
            btnReset.visibility = View.VISIBLE
            nilaiAkhir.visibility = View.VISIBLE
            nilaiHuruf.visibility = View.VISIBLE
        }
    }

    private fun resetHasil() {
        namaMahasiswa.text?.clear()
        nilaiKehadiran.text?.clear()
        nilaiTugas.text?.clear()
        nilaiAsesmen.text?.clear()
        tampilkanButton(false)
    }

    @SuppressLint("StringFormatInvalid")
    private fun hitungNilai(nama: String, kehadiran: String, tugas: String, asesmen: String) {
        // Validasi nama
        if(TextUtils.isEmpty(nama)) {
            Toast.makeText(this, "Harap masukkan nama!", Toast.LENGTH_LONG).show()
            return
        }

        // Validasi nilai
        if(TextUtils.isEmpty(kehadiran)) {
            Toast.makeText(this, "Harap masukkan nilai kehadiran!", Toast.LENGTH_LONG).show()
            return
        }
        if(TextUtils.isEmpty(tugas)) {
            Toast.makeText(this, "Harap masukkan nilai tugas!", Toast.LENGTH_LONG).show()
            return
        }
        if(TextUtils.isEmpty(asesmen)) {
            Toast.makeText(this, "Harap masukkan nilai asesmen!", Toast.LENGTH_LONG).show()
            return
        }

        // Hitung nilai
        val nilaiKehadiran = kehadiran.toFloat()
        val nilaiTugas = tugas.toFloat()
        val nilaiAsesmen = asesmen.toFloat()

        var hasil = 0f

        hasil = (nilaiKehadiran + nilaiTugas + nilaiAsesmen) / 3f

        var huruf: Char? = null

        if (hasil >= 81 && hasil <=100) {
            huruf = 'A'
        }
        else if (hasil >= 61 && hasil < 80) {
            huruf = 'B'
        }
        else if (hasil > 41 && hasil < 60) {
            huruf = 'C'
        }
        else if (hasil > 21 && hasil < 40) {
            huruf = 'D'
        }
        else if (hasil > 0 && hasil <= 20) {
            huruf = 'E'
        }
        val nilaiAkhirHuruf = huruf
        val hasilHuruf = nilaiAkhirHuruf.toString()

        nilaiAkhir.text = getString(R.string.nilaiAkhir, hasil)
        nilaiHuruf.text = getString(R.string.nilaiHuruf, hasilHuruf)

        tampilkanButton(true)

    }
}
