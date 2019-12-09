package com.android.tugasmvc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.tugasmvc.ScreenSlider.SliderActivity
import kotlinx.android.synthetic.main.splashscreen.*
import org.jetbrains.anko.intentFor


class Splashscreen  : AppCompatActivity() {
    private var iv: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.mytransition))
        startActivity(intentFor<SliderActivity>())
        val timer1 = object : Thread() {
            override fun run() {
                try {
                    sleep(5000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    finish()
                }
            }
        }
        timer1.start()
    }
}

