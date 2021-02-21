package com.shadowwingz.wanandroid.ui.article

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.ArticleListBean

class ArticleListAdapter(private var items: ArrayList<ArticleListBean>) :
        RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {
  
  private var hasRecorded: Boolean = false
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_list_item, parent, false)
    return ViewHolder(
            itemView
    )
  }
  
  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // todo declaration has type inferred from a platform call which can lead to unchecked nullability issues
    // 这里如果不用 var tvTime: TextView 这种写法，会报上面的警告，待研究
    var tvTime: TextView = itemView.findViewById(R.id.tv_time)
    var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    var tvCategory: TextView = itemView.findViewById(R.id.tv_category)
    var ivLike: ImageView = itemView.findViewById(R.id.iv_like)
  }
  
  override fun getItemCount(): Int {
    return items.size
  }
  
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    if (position == 0 && !hasRecorded) {
      hasRecorded = true
      val listener = object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
          holder.tvTime.viewTreeObserver.removeOnPreDrawListener(this)
//          LaunchTimer.endRecord("onBindViewHolder")
          return true
        }
      }
      holder.tvTime.viewTreeObserver.addOnPreDrawListener(listener)
    }
    holder.tvTime.text = items[position].niceDate
    holder.tvTitle.text = HtmlCompat.fromHtml(items[position].title, HtmlCompat.FROM_HTML_MODE_COMPACT)
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