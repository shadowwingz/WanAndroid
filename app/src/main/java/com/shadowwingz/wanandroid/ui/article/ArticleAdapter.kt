package com.shadowwingz.wanandroid.ui.article;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.ArticleListBean
import com.shadowwingz.wanandroid.databinding.ItemArticleBinding
import com.shadowwingz.wanandroid.listeners.OnItemClickListener

/**
 * created by shadowwingz on 2021-07-10 22:19
 */
class ArticleAdapter : PagingDataAdapter<ArticleListBean, ArticleAdapter.ViewHolder>(ARTICLE_COMPARATOR) {
  
  private var mOnItemClickListener: OnItemClickListener<ArticleListBean>? = null
  
  fun setOnItemClickListener(onItemClickListener: OnItemClickListener<ArticleListBean>) {
    mOnItemClickListener = onItemClickListener
  }
  
  companion object {
    private val ARTICLE_COMPARATOR = object : DiffUtil.ItemCallback<ArticleListBean>() {
      override fun areItemsTheSame(oldItem: ArticleListBean, newItem: ArticleListBean): Boolean {
        return oldItem.title == newItem.title
      }
      
      override fun areContentsTheSame(oldItem: ArticleListBean, newItem: ArticleListBean): Boolean {
        return oldItem == newItem
      }
    }
  }
  
  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
  
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    // val binding: ItemArticleBinding? = DataBindingUtil.getBinding(holder.itemView)
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
    // binding?.data = getItem(position)
    val binding = DataBindingUtil.getBinding<ItemArticleBinding>(holder.itemView)
    binding?.let {
      it.data = getItem(position)
      it.root.setOnClickListener(object : View.OnClickListener {
        override fun onClick(v: View?) {
          mOnItemClickListener?.onItemClick(it.data!!)
        }
        
      })
    }
  }
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding = DataBindingUtil.inflate<ViewDataBinding>(
      LayoutInflater.from(parent.context), R.layout.item_article, parent, false
    )
    return ViewHolder(binding.root)
  }
}
