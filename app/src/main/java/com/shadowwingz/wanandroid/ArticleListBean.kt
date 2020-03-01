package com.shadowwingz.wanandroid

class ArticleListBean {
    var time: String = ""
    var title: String = ""
    var category: String = ""
    var like: Boolean = false

    constructor(time: String, title: String, category: String, like: Boolean) {
        this.time = time
        this.title = title
        this.category = category
        this.like = like
    }
}