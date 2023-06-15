package org.d3if3135.mobpro1.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3135.mobpro1.R
import org.d3if3135.mobpro1.databinding.FragmentHitungBinding
import org.d3if3135.mobpro1.db.NilaiDb
import org.d3if3135.mobpro1.model.HasilNilai
import org.d3if3135.mobpro1.model.KategoriNilai


class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding
    private val viewModel: HitungViewModel by lazy {
        val db = NilaiDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonHitung.setOnClickListener { hitungNilai() }
        binding.buttonReset.setOnClickListener { resetHasil() }
        viewModel.getHasilNilai().observe(this, { showResult(it) })
        binding.saranButton.setOnClickListener { viewModel.mulaiNavigasi() }
        viewModel.getNavigasi().observe(viewLifecycleOwner) {
            if (it == null) return@observe
            findNavController().navigate(
                HitungFragmentDirections
                    .actionHitungFragmentToSaranFragment(it)
            )
            viewModel.selesaiNavigasi()
        }
        binding.shareButton.setOnClickListener { shareData() }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_historiFragment
                )
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
                )
                return true
            }
            R.id.menu_daftar -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_daftarFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareData() {
        val message = getString(
            R.string.bagikan_template,
            binding.namaInp.text,
            binding.nilaiAkhir.text,
            binding.nilaiHuruf.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager
            ) != null
        ) {
            startActivity(shareIntent)
        }
    }


    private fun resetHasil() {
        binding.namaInp.text?.clear()
        binding.kehadiranInp.text?.clear()
        binding.tugasInp.text?.clear()
        binding.asesmenInp.text?.clear()
        binding.nilaiAkhir.visibility = View.GONE
        binding.nilaiHuruf.visibility = View.GONE
        binding.buttonReset.visibility = View.GONE
        binding.saranButton.visibility = View.GONE
        binding.shareButton.visibility = View.GONE
    }

    private fun hitungNilai() {
        // Validasi nama
        val namaMahasiswa = binding.namaInp.text.toString()
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
            namaMahasiswa,
            nilaiKehadiran.toFloat(),
            nilaiTugas.toFloat(),
            nilaiAsesmen.toFloat()
        )
    }

    private fun showResult(result: HasilNilai?) {
        if (result == null) return
        binding.buttonReset.visibility = View.VISIBLE
        binding.buttonGroup.visibility = View.VISIBLE
        binding.nilaiAkhir.text = getString(R.string.nilaiAkhir, result.nilaiAkhir)
        binding.nilaiHuruf.text = getString(R.string.nilaiHuruf, result.nilaiHuruf)
        binding.nilaiAkhir.visibility = View.VISIBLE
        binding.nilaiHuruf.visibility = View.VISIBLE
    }
}