package org.d3if3135.mobpro1.ui.histori

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if3135.mobpro1.R
import org.d3if3135.mobpro1.databinding.ItemHistoriBinding
import org.d3if3135.mobpro1.db.NilaiEntity
import org.d3if3135.mobpro1.model.KategoriNilai
import org.d3if3135.mobpro1.model.hitungNilai
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter : ListAdapter<NilaiEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<NilaiEntity>() {
                override fun areItemsTheSame(
                    oldData: NilaiEntity, newData: NilaiEntity
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: NilaiEntity, newData: NilaiEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: NilaiEntity) = with(binding) {
            val hasilNilai = item.hitungNilai()
            kategoriTextView.text = hasilNilai.nilaiHuruf.toString().substring(0, 1)
            val colorRes = when (hasilNilai.nilaiHuruf) {
                KategoriNilai.A -> R.color.A
                KategoriNilai.B -> R.color.B
                KategoriNilai.C -> R.color.Ck
                KategoriNilai.D -> R.color.D
                else -> R.color.E
            }
            val circleBg = kategoriTextView.background as GradientDrawable
            circleBg.setColor(ContextCompat.getColor(root.context, colorRes))
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            nilaiAkhirTextView.text = root.context.getString(R.string.hasil_x,
                hasilNilai.nilaiAkhir, hasilNilai.nilaiHuruf.toString())

            dataTextView.text = root.context.getString(R.string.data_x,
                item.nama, item.kehadiran, item.tugas, item.asesmen)
        }
    }
}

