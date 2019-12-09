package com.android.tugasmvc.Galery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.tugasmvc.Login
import com.android.tugasmvc.R
import kotlinx.android.synthetic.main.activity_galery.*
import kotlinx.android.synthetic.main.layout_galery.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GaleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galery)

        refreshLayout.setOnRefreshListener {
            fetchGalery()
        }

        fetchGalery()
    }


    private fun fetchGalery() {
        refreshLayout.isRefreshing = true

        GaleryAPI().getGalery().enqueue(object : Callback<List<Galery>> {
            override fun onFailure(call: Call<List<Galery>>, t: Throwable) {
                refreshLayout.isRefreshing = false
                toast((t.message).toString())
//                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Galery>>, response: Response<List<Galery>>) {
                refreshLayout.isRefreshing = false
                val galery = response.body()

                galery?.let {
                    showGalery(it)
                }

            }


        })


    }





    private fun showGalery(galery: List<Galery>){
        rvGalery.layoutManager = LinearLayoutManager(this)
        rvGalery.adapter = GaleryAdapter(galery)
    }


}

//override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//
//    menuInflater.inflate(R.menu.menu_main, menu)
//    return true
//
//
//}
//
//override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){
//    R.id.action_logout ->{
//        val intent = Intent(this, Login::class.java)
//        true
//
//    }
//    else -> super.onOptionsItemSelected(item)
//}
//}

