package com.shadowwingz.wanandroid.ui.article

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
    // val binding: ItemArticleBinding? = DataBindingUtil.getBinding(holder.itemView)
    // binding?.data = item
    DataBindingUtil.getBinding<ItemArticleBinding>(holder.itemView)?.let {
      it.data = item
    }
  }
}