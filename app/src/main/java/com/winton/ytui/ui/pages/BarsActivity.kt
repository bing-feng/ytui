package com.winton.ytui.ui.pages

import android.annotation.SuppressLint
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.winton.ytui.R


class BarsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pages_bars)

        findViewById<ImageView>(R.id.image_back).setOnClickListener {
            finish()
        }

        val btn_more = findViewById<ImageView>(R.id.btn_more)
        btn_more.setOnClickListener {
            val y = btn_more.getBottom() * 5 + 30;
            val x = getWindowManager().getDefaultDisplay().getWidth();
            showMorePopupWindow(x, y);
        }

    }


    @SuppressLint("WrongConstant")
    fun showMorePopupWindow(x: Int, y: Int) {
        val more = arrayOf("我的相册", "我的收藏", "我的银行卡", "设置", "意见反馈")
        val layout = LayoutInflater.from(this@BarsActivity).inflate(
                R.layout.popupwindow_dropdown, null) as LinearLayout
        val listView = layout.findViewById(R.id.lv_dialog) as ListView
        listView.setAdapter(ArrayAdapter<String>(this@BarsActivity,
                R.layout.popupwindow_dropdown_text, R.id.tv_text, more))
        val popupWindow = PopupWindow(this@BarsActivity)
        popupWindow.setBackgroundDrawable(BitmapDrawable())
        popupWindow
                .setWidth(windowManager.defaultDisplay.width / 2)
        popupWindow.setHeight(420)
        popupWindow.setOutsideTouchable(true)
        popupWindow.setFocusable(true)
        popupWindow.setContentView(layout)
        popupWindow.showAtLocation(findViewById(R.id.pages_bars), Gravity.LEFT
                or Gravity.TOP, x, y) //需要指定Gravity，默认情况是center.
        listView.setOnItemClickListener(OnItemClickListener { arg0, arg1, arg2, arg3 ->
            Toast.makeText(baseContext, "您选择了：" + more.get(arg2), 1).show()
            popupWindow.dismiss()
        })
    }

}