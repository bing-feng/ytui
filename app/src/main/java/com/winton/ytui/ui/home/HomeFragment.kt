package com.winton.ytui.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.winton.ytui.R
import com.winton.ytui.ui.pages.BarsActivity
import com.winton.ytui.ui.pages.ButtonsActivity
import com.winton.ytui.ui.pages.IconsActivity
import com.winton.ytui.ui.pages.TabsActivity

class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<CardView>(R.id.cardView1).setOnClickListener {
            startActivity(Intent(this.context, ButtonsActivity::class.java))
        }
        view.findViewById<CardView>(R.id.cardView2).setOnClickListener {
            startActivity(Intent(this.context, IconsActivity::class.java))
        }
        view.findViewById<CardView>(R.id.cardView3).setOnClickListener {
            startActivity(Intent(this.context, BarsActivity::class.java))
        }
        view.findViewById<CardView>(R.id.cardView4).setOnClickListener {
            startActivity(Intent(this.context, TabsActivity::class.java))
        }
    }
}
