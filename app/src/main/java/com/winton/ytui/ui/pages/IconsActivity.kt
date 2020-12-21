package com.winton.ytui.ui.pages

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import com.winton.ytui.R

class IconsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pages_icons)

        findViewById<ImageView>(R.id.image_back).setOnClickListener {
            finish()
        }

        var list: ArrayList<Map<String, Any>> = ArrayList<Map<String, Any>>()
        var icons = arrayOf(R.drawable.icon_add, R.drawable.icon_add_bold, R.drawable.icon_add_circle, R.drawable.icon_arrow_down_bold
            , R.drawable.icon_arrow_down_filling, R.drawable.icon_arrow_left_bold, R.drawable.icon_arrow_left_filling
            , R.drawable.icon_arrow_right_bold, R.drawable.icon_arrow_right_filling, R.drawable.icon_arrow_up_bold
            , R.drawable.icon_arrow_up_filling, R.drawable.icon_close, R.drawable.icon_delete, R.drawable.icon_delete1
            , R.drawable.icon_download, R.drawable.icon_edit, R.drawable.icon_edit_circle, R.drawable.icon_error
            , R.drawable.icon_exit, R.drawable.icon_export, R.drawable.icon_help, R.drawable.icon_home, R.drawable.icon_info
            , R.drawable.icon_info1, R.drawable.icon_info2, R.drawable.icon_list, R.drawable.icon_list1, R.drawable.icon_locale
            , R.drawable.icon_login, R.drawable.icon_logout, R.drawable.icon_minus_bold, R.drawable.icon_more
            , R.drawable.icon_ok, R.drawable.icon_password, R.drawable.icon_profile, R.drawable.icon_question
            , R.drawable.icon_refresh, R.drawable.icon_scan, R.drawable.icon_success, R.drawable.icon_tel
            , R.drawable.icon_tool, R.drawable.icon_upload, R.drawable.icon_warning)
        var texts = arrayOf("icon_add","icon_add_bold","icon_add_circle","icon_arrow_down_bold",
            "icon_arrow_down_filling","icon_arrow_left_bold","icon_arrow_left_filling",
            "icon_arrow_right_bold","icon_arrow_right_filling","icon_arrow_up_bold",
            "icon_arrow_up_filling","icon_close","icon_delete","icon_delete1",
            "icon_download","icon_edit","icon_edit_circle","icon_error",
            "icon_exit","icon_export","icon_help","icon_home","icon_info",
            "icon_info1","icon_info2","icon_list","icon_list1","icon_locale",
            "icon_login","icon_logout","icon_minus_bold","icon_more",
            "icon_ok","icon_password","icon_profile","icon_question",
            "icon_refresh","icon_scan","icon_success","icon_tel",
            "icon_tool","icon_upload","icon_warning")
        for ((i, v) in icons.withIndex())  {
            var item: HashMap<String, Any>  = HashMap<String, Any>()
            item["text"] = texts[i]
            item["icon"] = icons[i]
            list.add(item)
        }

        findViewById<GridView>(R.id.gridView1).setAdapter(MyAdapter1(this, list))
        findViewById<GridView>(R.id.gridView1).setSelector(ColorDrawable(Color.TRANSPARENT))
    }

    class MyAdapter1(context: Context, list: List<Map<String, Any>>) : BaseAdapter() {
        private var data: List<Map<String, Any>> = list
        private var mContext: Context? = context

        override fun getCount(): Int {
            return data.size
        }

        override fun getItem(arg0: Int): Any {
            return data[arg0]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        @SuppressLint("ViewHolder", "InflateParams")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater = LayoutInflater.from(mContext)
            val view: View = inflater.inflate(R.layout.activity_pages_icons_grid_item, null)

            val ImageView1 = view.findViewById<ImageView>(R.id.list_image)
            val txt1 = view.findViewById<TextView>(R.id.list_text)

            val icon = data[position].get("icon")
            ImageView1.setImageResource(icon as Int)
            txt1.text = data[position].get("text").toString()

            return view
        }
    }
}