package org.d3if3135.mobpro1.ui.daftar

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if3135.mobpro1.R
import org.d3if3135.mobpro1.databinding.ItemDaftarBinding
import org.d3if3135.mobpro1.model.NamaOrang
import org.d3if3135.mobpro1.network.NamaOrangApi

class DaftarAdapter : RecyclerView.Adapter<DaftarAdapter.ViewHolder>() {
    private val data = mutableListOf<NamaOrang>()

    fun updateData(newData: List<NamaOrang>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemDaftarBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(namaOrang: NamaOrang) = with(binding) {
            namaOrangTextView.text = namaOrang.nama
            deskripsiTextView.text = namaOrang.deskripsi
            Glide.with(imageView.context)
                .load(NamaOrangApi.getNamaOrangUrl(namaOrang.gambar))
                .error(R.drawable.baseline_broken_image_24)
                .into(imageView)

            root.setOnClickListener {
                val message = root.context.getString(R.string.message, namaOrang.nama)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDaftarBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}