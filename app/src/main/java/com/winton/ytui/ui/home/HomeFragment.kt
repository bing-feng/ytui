package com.winton.ytui.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.forEach
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

        arrayOf(R.id.cardView1, R.id.cardView2, R.id.cardView3, R.id.cardView4).forEach {
            item -> when(item) {
                R.id.cardView1 -> openActivity(view, item, ButtonsActivity::class.java)
                R.id.cardView2 -> openActivity(view, item, IconsActivity::class.java)
                R.id.cardView3 -> openActivity(view, item, BarsActivity::class.java)
                R.id.cardView4 -> openActivity(view, item, TabsActivity::class.java)
            }
        }
    }
    private fun openActivity(view: View, id: Int, targetActivityClass: Class<*>) {
        view.findViewById<CardView>(id).setOnClickListener {
            startActivity(Intent(this.context, targetActivityClass))
        }
    }
}
