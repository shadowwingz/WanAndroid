package com.shadowwingz.wanandroid.ui.article

import com.shadowwingz.wanandroid.R

enum class SubTitleItem(var icon: Int, var subTitle: String, var subDesc: String = "") {

    MY_COIN(R.drawable.ic_coin, "我的积分", "771"),
    MY_SHARE(R.drawable.ic_share_article, "我的分享"),
    MY_COLLECTION(R.drawable.ic_collect, "我的收藏"),
    READ_LATER(R.drawable.ic_read_later, "稍后阅读"),
    READ_RECORD(R.drawable.ic_read_record, "阅读历史"),
    GITHUB(R.drawable.ic_github, "开源项目"),
    ABOUT(R.drawable.ic_about, "关于作者", "请他喝杯咖啡~"),
    SETTINGS(R.drawable.ic_setting, "系统设置")
}