package com.winton.ytui.ui.pages

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.winton.ytui.R

class ModalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pages_modal)

        findViewById<ImageView>(R.id.image_back).setOnClickListener {
            finish()
        }
    }
}