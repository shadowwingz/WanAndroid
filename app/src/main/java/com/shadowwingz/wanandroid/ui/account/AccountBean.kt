package com.shadowwingz.wanandroid.ui.account;

/**
 * created by shadowwingz on 2021-06-21 22:49
 */
data class AccountBean(
    val `data`: Data,
    val errorCode: Int,
    val errorMsg: String
)

data class Data(
    val admin: Boolean,
    val chapterTops: List<Any>,
    val coinCount: Int,
    val collectIds: List<Int>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
)