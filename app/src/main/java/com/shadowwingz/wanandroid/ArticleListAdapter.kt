package com.shadowwingz.wanandroid

import android.os.Build
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shadowwingz.wanandroid.bean.ArticleListBean

class ArticleListAdapter(private var items: ArrayList<ArticleListBean>) :
    RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_list_item, parent, false)
        return ViewHolder(itemView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // todo declaration has type inferred from a platform call which can lead to unchecked nullability issues
        // 这里如果不用 var tvTime: TextView 这种写法，会报上面的警告，待研究
        var tvTime: TextView = itemView.findViewById(R.id.tv_time)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var tvCategory: TextView = itemView.findViewById(R.id.tv_category)
        var ivLike: ImageView = itemView.findViewById(R.id.iv_like)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTime.text = items[position].niceDate
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tvTitle.text = Html.fromHtml(items[position].title, FROM_HTML_MODE_LEGACY)
        } else {
            holder.tvTitle.text = Html.fromHtml(items[position].title)
        }
        holder.tvCategory.text = items[position].superChapterName
        if (items[position].collect) {
            holder.ivLike.setImageResource(R.drawable.ic_like)
        } else {
            holder.ivLike.setImageResource(R.drawable.ic_like)
        }
    }

    fun setData(data: ArrayList<ArticleListBean>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}