package com.winton.ytui.ui.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.winton.ytui.R

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pages_card)

        findViewById<ImageView>(R.id.image_back).setOnClickListener {
            finish()
        }
    }
}