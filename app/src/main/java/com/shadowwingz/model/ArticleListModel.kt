package com.shadowwingz.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.shadowwingz.bean.ArticleListBean
import com.shadowwingz.utils.SLog

class ArticleListModel(application: Application) : AndroidViewModel(application) {

    var mutableLiveData = MutableLiveData<List<ArticleListBean>>()

    fun setArticleList(articleList: List<ArticleListBean>) {
        mutableLiveData.value = articleList
    }

    fun getArticleList(): MutableLiveData<List<ArticleListBean>> {
        return mutableLiveData
    }

    override fun onCleared() {
        super.onCleared()
        SLog.d("ArticleListModel onCleared")
    }
}