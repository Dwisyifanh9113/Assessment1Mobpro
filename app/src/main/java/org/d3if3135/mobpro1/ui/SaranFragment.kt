package org.d3if3135.mobpro1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.d3if3135.mobpro1.R
import org.d3if3135.mobpro1.databinding.FragmentSaranBinding
import org.d3if3135.mobpro1.model.KategoriNilai

class SaranFragment : Fragment() {
    private lateinit var binding: FragmentSaranBinding
    private val args: SaranFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaranBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun updateUI(kategori: KategoriNilai) {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        when (kategori) {
            KategoriNilai.A -> {
                actionBar?.title = getString(R.string.nilai_a)
                binding.imageView.setImageResource(R.drawable.a)
                binding.textView.text = getString(R.string.saran_a)
            }
            KategoriNilai.B -> {
                actionBar?.title = getString(R.string.nilai_b)
                binding.imageView.setImageResource(R.drawable.b)
                binding.textView.text = getString(R.string.saran_b)
            }
            KategoriNilai.C -> {
                actionBar?.title = getString(R.string.nilai_c)
                binding.imageView.setImageResource(R.drawable.c)
                binding.textView.text = getString(R.string.saran_c)
            }
            KategoriNilai.D -> {
                actionBar?.title = getString(R.string.nilai_d)
                binding.imageView.setImageResource(R.drawable.d)
                binding.textView.text = getString(R.string.saran_d)
            }
            KategoriNilai.E -> {
                actionBar?.title = getString(R.string.nilai_e)
                binding.imageView.setImageResource(R.drawable.e)
                binding.textView.text = getString(R.string.saran_e)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI(args.kategori)
    }
}