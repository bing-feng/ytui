package com.winton.ytui.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        view.findViewById<TextView>(R.id.tv1).setOnClickListener {
            startActivity(Intent(this.context, ProfileActivity::class.java))
        }
        view.findViewById<TextView>(R.id.tv2).setOnClickListener {
            startActivity(Intent(this.context, ProfileActivity::class.java))
        }
        view.findViewById<TextView>(R.id.tv3).setOnClickListener {
            startActivity(Intent(this.context, ProfileActivity::class.java))
        }
        view.findViewById<TextView>(R.id.tv4).setOnClickListener {
            startActivity(Intent(this.context, ProfileActivity::class.java))
        }
    }
}
