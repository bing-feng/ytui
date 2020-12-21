package com.winton.ytui.ui.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.winton.ytui.R

class BarsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pages_bars)

        findViewById<ImageView>(R.id.image_back).setOnClickListener {
            finish()
        }
    }
}