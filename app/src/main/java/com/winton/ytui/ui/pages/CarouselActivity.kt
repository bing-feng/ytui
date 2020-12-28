package com.winton.ytui.ui.pages

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.winton.ytui.R
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader


class CarouselActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pages_carousel)

        initBanner();
    }
    private fun initBanner() {
        val imageUrlData = ArrayList<Int>()
        val contentData = ArrayList<String>()
        imageUrlData.add(R.drawable.bg_top_3)
        imageUrlData.add(R.drawable.bg_top_2)
        imageUrlData.add(R.drawable.bg_top_7)
        imageUrlData.add(R.drawable.bg_top_4)
        contentData.add("图片一");
        contentData.add("图片二");
        contentData.add("图片三");
        contentData.add("图片四");

        findViewById<Banner>(R.id.banner)
            .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
            .setImageLoader(MyLoader())
            .setImages(imageUrlData)
            .setBannerTitles(contentData)
            .setBannerAnimation(Transformer.Default)
            .setDelayTime(5000) //切换频率
            .isAutoPlay(true) //自动启动
            .setIndicatorGravity(BannerConfig.CENTER) //位置设置
            .start() //开始运行

    }
    private class MyLoader : ImageLoader() {
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
//            Glide.with(context).load(path).into(imageView)
            imageView?.setImageResource(path as Int)
        }
    }
}