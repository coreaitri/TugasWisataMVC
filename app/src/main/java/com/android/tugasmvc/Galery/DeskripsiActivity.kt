package com.android.tugasmvc.Galery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.tugasmvc.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_deskripsi.*
import kotlinx.android.synthetic.main.layout_galery.*
import kotlinx.android.synthetic.main.layout_galery.view.*

class DeskripsiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deskripsi)

        val gambar = intent.getStringExtra("image")
        val nama  = intent.getStringExtra("panorama")
        val deskripsi = intent.getStringExtra("deskripsi")
        val latitude = intent.getStringExtra("latitude")
        val longitude = intent.getStringExtra("longitude")


        TVpanorama.setText(nama)
        TVdeskripsi.setText(deskripsi)
        Glide.with(this)
            .load(gambar)
            .into(imageView2)


        btn_map.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("panorama", nama)
            intent.putExtra("latitude", latitude)
            intent.putExtra("longitude", longitude)
            startActivity(intent)
        }
    }
}
