package com.winton.ytui.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.winton.ytui.R
import com.winton.ytui.ui.pages.*

class NotificationsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrayOf(R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4).forEach {
            item -> when(item) {
                R.id.tv1 -> openActivity(view, item, ProfileActivity::class.java)
                R.id.tv2 -> openActivity(view, item, ProfileActivity::class.java)
                R.id.tv3 -> openActivity(view, item, ProfileActivity::class.java)
                R.id.tv4 -> openActivity(view, item, ProfileActivity::class.java)
            }
        }
    }
    private fun openActivity(view: View, id: Int, targetActivityClass: Class<*>) {
        view.findViewById<TextView>(id).setOnClickListener {
            startActivity(Intent(this.context, targetActivityClass))
        }
    }
}
