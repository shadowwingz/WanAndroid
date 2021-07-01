package com.shadowwingz.wanandroid.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.ArticleListBean

class ArticleItemViewBinder : ItemViewBinder<ArticleListBean, ArticleItemViewBinder.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTime: TextView = itemView.findViewById(R.id.tv_time)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvCategory: TextView = itemView.findViewById(R.id.tv_category)
        val ivLike: ImageView = itemView.findViewById(R.id.iv_like)
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.article_list_item, parent, false)
//        return ViewHolder(binding.root)
        return ViewHolder(inflater.inflate(R.layout.item_article, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: ArticleListBean) {
        holder.tvTime.text = item.niceDate
        holder.tvTitle.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
        holder.tvCategory.text = item.superChapterName
        if (item.collect) {
            holder.ivLike.setImageResource(R.drawable.ic_like)
        } else {
            holder.ivLike.setImageResource(R.drawable.ic_like)
        }
    }
}