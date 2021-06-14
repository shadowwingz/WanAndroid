package com.shadowwingz.wanandroid.bean

import androidx.annotation.Keep

@Keep
data class BannerBean(
        val data: List<BannerData>
)

@Keep
data class BannerData(
        val desc: String,
        val id: Int,
        val imagePath: String,
        val isVisible: Int,
        val order: Int,
        val title: String,
        val type: Int,
        val url: String
)