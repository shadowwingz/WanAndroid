package com.shadowwingz.wanandroid.bean

/**
 * 首页 banner
 */
data class BannerBean(
        val data: List<BannerData>
)

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