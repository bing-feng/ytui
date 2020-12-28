package com.winton.ytui.ui.pages

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.winton.ytui.R
import kotlinx.android.synthetic.main.activity_pages_layout.*

class LayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pages_layout)

        val drawer_layout = findViewById<DrawerLayout>(R.id.drawer_layout)
        findViewById<Button>(R.id.btn_open_left).setOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }
        findViewById<Button>(R.id.btn_open_right).setOnClickListener {
            drawer_layout.openDrawer(GravityCompat.END)
        }
        //关闭执行DrawerLayout
        findViewById<Button>(R.id.btn_close_right).setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.END)
        }
        //监听
        drawer_layout.addDrawerListener(object: DrawerLayout.DrawerListener {
            override fun onDrawerSlide(view: View, v: Float) { }
            override fun onDrawerOpened(view: View) {
                if(view.id == R.id.navigation_view)
                    findViewById<ImageView>(R.id.image_menu).setImageResource(R.drawable.icon_close)
            }
            override fun onDrawerClosed(view: View) {
                if(view.id == R.id.navigation_view)
                    findViewById<ImageView>(R.id.image_menu).setImageResource(R.drawable.icon_list)
            }
            override fun onDrawerStateChanged(i: Int) { }
        })
        // 左侧menu激活
        findViewById<ImageView>(R.id.image_menu).setOnClickListener { item ->
            if(drawer_layout.isDrawerOpen(GravityCompat.START))
                drawer_layout.closeDrawer(GravityCompat.START)
            else
                drawer_layout.openDrawer(GravityCompat.START)
        }

        findViewById<NavigationView>(R.id.navigation_view).setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.nav_layout_item1 -> openActivity(ProfileActivity::class.java)
                R.id.nav_layout_item2 -> openActivity(ProfileActivity::class.java)
                R.id.nav_layout_item3 -> openActivity(ProfileActivity::class.java)
                R.id.nav_layout_item4 -> openActivity(ProfileActivity::class.java)
                R.id.nav_layout_item5 -> finish()
            }
            return@setNavigationItemSelectedListener false
        }
    }


    private fun openActivity(targetActivityClass: Class<*>) {
        startActivity(Intent(this, targetActivityClass))
    }

}