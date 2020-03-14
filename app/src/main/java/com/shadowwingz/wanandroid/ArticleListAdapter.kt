package com.shadowwingz.wanandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticleListAdapter(private val items: List<ArticleListBean>) :
    RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_list_item, parent, false)
        return ViewHolder(itemView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // todo declaration has type inferred from a platform call which can lead to unchecked nullability issues
        // 这里如果不用 var tvTime: TextView 这种写法，会报上面的警告，待研究
        var tvTime: TextView = itemView.findViewById<TextView>(R.id.tv_time)
        var tvTitle: TextView = itemView.findViewById<TextView>(R.id.tv_title)
        var tvCategory: TextView = itemView.findViewById<TextView>(R.id.tv_category)
        var ivLike: ImageView = itemView.findViewById<ImageView>(R.id.iv_like)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTime.text = items[position].time
        holder.tvTitle.text = items[position].title
        holder.tvCategory.text = items[position].category
        if (items[position].like) {
            holder.ivLike.setImageResource(R.drawable.ic_like)
        } else {
            holder.ivLike.setImageResource(R.drawable.ic_like)
        }
    }
}