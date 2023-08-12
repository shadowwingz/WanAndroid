package com.shadowwingz.wanandroid.ui.article;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.databinding.ItemArticleBinding

/**
 * created by shadowwingz on 2021-07-10 22:19
 */
class ArticleAdapter : PagingDataAdapter<ArticleListUiModel, ArticleAdapter.ViewHolder>(ARTICLE_COMPARATOR) {

  companion object {
    private val ARTICLE_COMPARATOR = object : DiffUtil.ItemCallback<ArticleListUiModel>() {
      override fun areItemsTheSame(oldItem: ArticleListUiModel, newItem: ArticleListUiModel): Boolean {
        return oldItem.title == newItem.title
      }

      override fun areContentsTheSame(oldItem: ArticleListUiModel, newItem: ArticleListUiModel): Boolean {
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
    /*val binding = DataBindingUtil.getBinding<ItemArticleBinding>(holder.itemView)
    binding?.let {
      it.data = getItem(position)
      it.root.setOnClickListener(object : View.OnClickListener {
        override fun onClick(v: View?) {
          mOnItemClickListener?.onItemClick(it.data!!)
        }
        
      })
    }*/

    val binding = ItemArticleBinding.bind(holder.itemView)

    val data = getItem(position)
    binding.apply {
      data?.let { article ->
        tvAuthor.text = article.author
        tvTime.text = article.date
        tvTitle.text = article.title
        tvSuperChapterName.text = article.superChapterName
        tvChapterName.text = article.chapterName
        ivLike.setImageDrawable(AppCompatResources.getDrawable(root.context, article.collectIcon))

        root.setOnClickListener {
          data.itemClickListener()
        }
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
    /*val binding = DataBindingUtil.inflate<ViewDataBinding>(
      LayoutInflater.from(parent.context), R.layout.item_article, parent, false
    )*/
    return ViewHolder(view)
  }
}
