package com.winton.ytui.ui.pages

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.view.OptionsPickerView
import com.winton.ytui.R
import com.winton.ytui.ui.common.provincepicker.GetJsonDataUtil
import com.winton.ytui.ui.common.provincepicker.JsonBean
import com.zaaach.citypicker.CityPicker
import com.zaaach.citypicker.adapter.OnPickListener
import com.zaaach.citypicker.model.City
import com.zaaach.citypicker.model.HotCity
import com.zaaach.citypicker.model.LocatedCity
import java.text.SimpleDateFormat


class FormsActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pages_forms)
        initJsonData()

        findViewById<ImageView>(R.id.image_back).setOnClickListener {
            finish()
        }

        val city = findViewById<EditText>(R.id.editTextTextPostalAddress)
        val province = findViewById<EditText>(R.id.editTextTextPostalAddress1)
        val date = findViewById<EditText>(R.id.editTextDate)
        val time = findViewById<EditText>(R.id.editTextTime)

        city.setInputType(EditorInfo.TYPE_NULL)
        province.setInputType(EditorInfo.TYPE_NULL)
        date.setInputType(EditorInfo.TYPE_NULL)
        time.setInputType(EditorInfo.TYPE_NULL)
        city.setOnTouchListener{ it: View, motionEvent: MotionEvent ->
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) cityPicker() else false
        }
        province.setOnTouchListener{ it: View, motionEvent: MotionEvent ->
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) provincePicker() else false
        }
        date.setOnTouchListener{ it: View, motionEvent: MotionEvent ->
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) timePicker(true, it as EditText) else false
        }
        time.setOnTouchListener{ it: View, motionEvent: MotionEvent ->
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) timePicker(false, it as EditText) else false
        }
    }

    // 城市选择器
    private fun cityPicker(): Boolean {
        val hotCities: MutableList<HotCity> = ArrayList()
        hotCities.add(HotCity("北京", "北京", "101010100")) //code为城市代码
        hotCities.add(HotCity("上海", "上海", "101020100"))
        hotCities.add(HotCity("广州", "广东", "101280101"))
        hotCities.add(HotCity("深圳", "广东", "101280601"))
        hotCities.add(HotCity("杭州", "浙江", "101210101"))
        CityPicker.from(this) //activity或者fragment
            .enableAnimation(true)	//启用动画效果，默认无
            .setLocatedCity(LocatedCity("杭州", "浙江", "101210101"))  //APP自身已定位的城市，传null会自动定位（默认）
            .setHotCities(hotCities)	//指定热门城市
            .setOnPickListener(object : OnPickListener {
                override fun onPick(position: Int, data: City) {
                    findViewById<EditText>(R.id.editTextTextPostalAddress).setText(data.name)
                }

                override fun onCancel() {}
                override fun onLocate() {}
            })
            .show()
        return true;
    }
    // 省市县三级联动
    private var options1Items: ArrayList<JsonBean> = ArrayList()
    private val options2Items: ArrayList<ArrayList<String>> = ArrayList()
    private val options3Items: ArrayList<ArrayList<ArrayList<String>>> = ArrayList()
    private fun provincePicker(): Boolean {
        val pvOptions: OptionsPickerView<*> = OptionsPickerBuilder(
                this
        ) { options1, options2, options3, v -> //返回的分别是三个级别的选中位置
            val opt1tx =
                if (options1Items.size > 0) options1Items.get(options1).getPickerViewText() else ""
            val opt2tx = if (options2Items.size > 0
                && options2Items.get(options1).size > 0
            ) options2Items.get(options1).get(options2) else ""
            val opt3tx = if (options2Items.size > 0 && options3Items.get(options1)
                    .size > 0 && options3Items.get(options1).get(options2).size > 0
            ) options3Items.get(options1).get(options2).get(options3) else ""
            val tx = opt1tx + opt2tx + opt3tx
            findViewById<EditText>(R.id.editTextTextPostalAddress1).setText(tx)
        }
            .setTitleText("城市选择")
            .setDividerColor(Color.BLACK)
            .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
            .setContentTextSize(20)
            .build<Any>()

//        pvOptions.setPicker(options1Items);//一级选择器
//        pvOptions.setPicker(options1Items, options2Items);//二级选择器

        pvOptions.setPicker(
                cast(options1Items),
                cast(options2Items),
                cast(options3Items)
        ) //三级选择器

        pvOptions.show()
        return true
    }
    @Suppress("UNCHECKED_CAST")
    fun <T> cast(obj: Any): T {
        return obj as T
    }
    private fun initJsonData() { //解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         */
        val JsonData = GetJsonDataUtil().getJson(this, "province.json") //获取assets目录下的json文件数据
        val jsonBean: ArrayList<JsonBean> = GetJsonDataUtil().parseData(JsonData) //用Gson 转成实体
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean
        for (i in 0 until jsonBean.size) { //遍历省份
            val cityList: ArrayList<String> = ArrayList() //该省的城市列表（第二级）
            val province_AreaList: ArrayList<ArrayList<String>> = ArrayList() //该省的所有地区列表（第三极）
            for (c in 0 until (jsonBean[i].city?.size ?: 0)) { //遍历该省份的所有城市
                val cityName: String = jsonBean[i].city?.get(c)?.name ?: ""
                cityList.add(cityName)
                //添加城市
                val city_AreaList: ArrayList<String> = ArrayList() //该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean[i].city?.get(c)?.area == null
                        || jsonBean[i].city?.get(c)?.area?.size == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean[i].city?.get(c)?.area!!);
                }
                province_AreaList.add(city_AreaList) //添加该省所有地区数据
            }
            /**
             * 添加城市数据
             */
            options2Items.add(cityList)
            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList)
        }
    }

    // timePicker
    @SuppressLint("SimpleDateFormat")
    private fun timePicker(isDate: Boolean, txt: EditText): Boolean {
        // 年月日时分秒
        val booleanArray = if(isDate)
            booleanArrayOf(true, true, true, false, false, false)
        else booleanArrayOf(true, true, true, true, true, true)

        val pvTime = TimePickerBuilder(this) { date, v ->
            txt.setText(
                    if (isDate) SimpleDateFormat("yyyy-MM-dd").format(date)
                    else SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)
            )
        }
        .setType(booleanArray)
        .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
        .setItemVisibleCount(5) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
        .setLineSpacingMultiplier(2.0f)
        .isAlphaGradient(true)
        .build()
        val mDialog: Dialog = pvTime.getDialog()
        val params = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.BOTTOM
        )
        params.leftMargin = 0
        params.rightMargin = 0
        pvTime.getDialogContainerLayout().setLayoutParams(params)
        val dialogWindow: Window? = mDialog.getWindow()
        if (dialogWindow != null) {
            dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim) //修改动画样式
            dialogWindow.setGravity(Gravity.BOTTOM) //改成Bottom,底部显示
            dialogWindow.setDimAmount(0.3f)
        }
        pvTime.show()
        return true
    }

}