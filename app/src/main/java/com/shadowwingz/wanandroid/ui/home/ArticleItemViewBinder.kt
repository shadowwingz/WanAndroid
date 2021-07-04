package com.shadowwingz.wanandroid.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.ArticleListBean
import com.shadowwingz.wanandroid.databinding.ItemArticleBinding

class ArticleItemViewBinder : ItemViewBinder<ArticleListBean, ArticleItemViewBinder.ViewHolder>() {

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
    val binding = DataBindingUtil.inflate<ViewDataBinding>(
      LayoutInflater.from(parent.context), R.layout.item_article, parent, false
    )
    return ViewHolder(binding.root)
  }

  override fun onBindViewHolder(holder: ViewHolder, item: ArticleListBean) {
    val binding: ItemArticleBinding? = DataBindingUtil.getBinding(holder.itemView)
    /**
     * 第一个参数 BR.data 是根据 item_article.xml 中定义的 variable 生成的。
     *
     * 我们在 item_article.xml 定义的 variable 的 name 是 data，所以我们
     *
     * <variable
     *    name="data"
     *    type="com.shadowwingz.wanandroid.bean.ArticleListBean" />
     */
    // binding?.setVariable(BR.data, item)
    // 效果等同于：
    binding?.data = item
  }
}