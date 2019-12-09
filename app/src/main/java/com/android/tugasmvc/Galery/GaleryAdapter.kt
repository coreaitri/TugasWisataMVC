package com.android.tugasmvc.Galery

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.tugasmvc.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_galery.view.*

class GaleryAdapter(val galery: List<Galery>): RecyclerView.Adapter<GaleryAdapter.GaleryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GaleryViewHolder {
        return GaleryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_galery, parent, false)
        )
    }

    override fun getItemCount() = galery.size

    override fun onBindViewHolder(holder: GaleryViewHolder, position: Int) {
        val galeri = galery[position]
        holder.view.tvPanorama.text = galeri.panorama
        holder.view.tvCity.text = galeri.city
        holder.view.tvMap.text = galeri.lokasi
        Glide.with(holder.view.context)
            .load(galeri.image)
            .into(holder.view.imageView)

        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, DeskripsiActivity::class.java)
            intent.putExtra("panorama", galeri.panorama)
            intent.putExtra("city", galeri.city)
            intent.putExtra("deskripsi", galeri.deskripsi)
            intent.putExtra("image", galeri.image)
            intent.putExtra("lokasi", galeri.lokasi)
            intent.putExtra("latitude", galeri.latitude)
            intent.putExtra("longitude", galeri.longitude)
            it.context.startActivity(intent)
        }


    }


    class GaleryViewHolder(val view: View): RecyclerView.ViewHolder(view)

}
