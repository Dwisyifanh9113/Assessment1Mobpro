package org.d3if3135.mobpro1.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if3135.mobpro1.R
import org.d3if3135.mobpro1.databinding.FragmentHitungBinding
import org.d3if3135.mobpro1.model.HasilNilai


class HitungFragment : Fragment() {

    private lateinit var binding : FragmentHitungBinding
//    lateinit var namaMahasiswa : com.google.android.material.textfield.TextInputEditText
//    lateinit var nilaiKehadiran : com.google.android.material.textfield.TextInputEditText
//    lateinit var nilaiTugas : com.google.android.material.textfield.TextInputEditText
//    lateinit var nilaiAsesmen : com.google.android.material.textfield.TextInputEditText
//    lateinit var btnHitung : Button
//    lateinit var btnReset : Button
//    lateinit var nilaiAkhir : TextView
//    lateinit var nilaiHuruf : TextView

    private val viewModel: HitungViewModel by lazy {
        ViewModelProvider(requireActivity())[HitungViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
//        super.onCreate(savedInstanceState)
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)


        setHasOptionsMenu(true)
        return binding.root
    }

//        setContentView(binding.root)

//        namaMahasiswa = binding.namaInp
//        nilaiKehadiran = binding.kehadiranInp
//        nilaiTugas = binding.tugasInp
//        nilaiAsesmen = binding.asesmenInp
//        btnHitung = binding.buttonHitung
//        btnReset = binding.buttonReset
//        nilaiAkhir = binding.nilaiAkhir
//        nilaiHuruf = binding.nilaiHuruf

//        btnHitung.setOnClickListener {
//            hitungNilai(
//                namaMahasiswa.text.toString(),
//                nilaiKehadiran.text.toString(),
//                nilaiTugas.text.toString(),
//                nilaiAsesmen.text.toString(),
//            )
//        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        tampilkanButton(false)
        binding.buttonHitung.setOnClickListener{hitungNilai()}
        binding.buttonReset.setOnClickListener { resetHasil() }
        viewModel.getHasilNilai().observe(this, { showResult(it) })
        binding.saranButton.setOnClickListener {viewModel.mulaiNavigasi()}
        viewModel.getNavigasi().observe(viewLifecycleOwner) {
            if (it == null) return@observe
            findNavController().navigate(HitungFragmentDirections
                .actionHitungFragmentToSaranFragment(it))
            viewModel.selesaiNavigasi()
        }
        binding.shareButton.setOnClickListener{shareData()}


//        binding.shareButton.setOnClickListener { shareData() }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_hitungFragment_to_aboutFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareData() {
        val message = getString(R.string.bagikan_template,
            binding.namaInp.text,
            binding.nilaiAkhir.text,
            binding.nilaiHuruf.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }

//    private fun tampilkanButton(btn: Boolean) {
//        if(!btn) {
//            btnReset.visibility = View.INVISIBLE
//            nilaiAkhir.visibility = View.INVISIBLE
//            nilaiHuruf.visibility = View.INVISIBLE
//        }else {
//            btnReset.visibility = View.VISIBLE
//            nilaiAkhir.visibility = View.VISIBLE
//            nilaiHuruf.visibility = View.VISIBLE
//        }
//    }

    private fun resetHasil() {
        binding.namaInp.text?.clear()
        binding.kehadiranInp.text?.clear()
        binding.tugasInp.text?.clear()
        binding.asesmenInp.text?.clear()
//        tampilkanButton(false)
    }


    private fun hitungNilai() {
        // Validasi nama
        val namaMahasiswa = binding.namaInp.text
        if (TextUtils.isEmpty(namaMahasiswa)) {
            Toast.makeText(context, "Harap masukkan nama!", Toast.LENGTH_LONG).show()
            return
        }

        // Validasi nilai
        val nilaiKehadiran = binding.kehadiranInp.text.toString()
        if (TextUtils.isEmpty(nilaiKehadiran)) {
            Toast.makeText(context, "Harap masukkan nilai kehadiran!", Toast.LENGTH_LONG).show()
            return
        }
        val nilaiTugas = binding.tugasInp.text.toString()
        if (TextUtils.isEmpty(nilaiTugas)) {
            Toast.makeText(context, "Harap masukkan nilai tugas!", Toast.LENGTH_LONG).show()
            return
        }
        val nilaiAsesmen = binding.asesmenInp.text.toString()
        if (TextUtils.isEmpty(nilaiAsesmen)) {
            Toast.makeText(context, "Harap masukkan nilai asesmen!", Toast.LENGTH_LONG).show()
            return
        }
        viewModel.hitungNilai(
            nilaiKehadiran.toFloat(),
            nilaiTugas.toFloat(),
            nilaiAsesmen.toFloat()
        )
    }
    private fun showResult(result: HasilNilai?){
        if (result == null) return
        binding.buttonReset.visibility = View.VISIBLE
        binding.buttonGroup.visibility = View.VISIBLE
        binding.nilaiAkhir.text = getString(R.string.nilaiAkhir, result.nilaiAkhir)
        binding.nilaiHuruf.text = getString(R.string.nilaiHuruf,result.nilaiHuruf)
        binding.nilaiAkhir.visibility = View.VISIBLE
        binding.nilaiHuruf.visibility = View.VISIBLE
    }
}


        // Hitung nilai
//        val nilaiKehadiran = kehadiran.toFloat()
//        val nilaiTugas = tugas.toFloat()
//        val nilaiAsesmen = asesmen.toFloat()
//
//        var hasil = 0f

//        hasil = (nilaiKehadiran + nilaiTugas + nilaiAsesmen) / 3f
//
//        var huruf: Char? = null
//
////        if (hasil >= 81 && hasil <=100) {
////            huruf = 'A'
////        }
////        else if (hasil >= 61 && hasil < 80) {
////            huruf = 'B'
////        }
////        else if (hasil > 41 && hasil < 60) {
////            huruf = 'C'
////        }
////        else if (hasil > 21 && hasil < 40) {
////            huruf = 'D'
////        }
////        else if (hasil > 0 && hasil <= 20) {
////            huruf = 'E'
////        }
//        val nilaiAkhirHuruf = huruf
//        val hasilHuruf = nilaiAkhirHuruf.toString()
//
//        nilaiAkhir.text = getString(R.string.nilaiAkhir, hasil)
//        nilaiHuruf.text = getString(R.string.nilaiHuruf, hasilHuruf)
//
//        tampilkanButton(true)