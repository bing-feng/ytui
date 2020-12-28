package com.winton.ytui.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.winton.ytui.R
import com.winton.ytui.ui.pages.*

class DashboardFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrayOf(R.id.cardView1, R.id.cardView2, R.id.cardView3, R.id.cardView4, R.id.cardView5, R.id.cardView6).forEach {
            item -> when(item) {
                R.id.cardView1 -> openActivity(view, item, ListActivity::class.java)
                R.id.cardView2 -> openActivity(view, item, CardActivity::class.java)
                R.id.cardView3 -> openActivity(view, item, FormsActivity::class.java)
                R.id.cardView4 -> openActivity(view, item, CarouselActivity::class.java)
                R.id.cardView5 -> openActivity(view, item, ModalActivity::class.java)
                R.id.cardView6 -> openActivity(view, item, LayoutActivity::class.java)
            }
        }
    }
    private fun openActivity(view: View, id: Int, targetActivityClass: Class<*>) {
        view.findViewById<CardView>(id).setOnClickListener {
            startActivity(Intent(this.context, targetActivityClass))
        }
    }
}
