package com.shadowwingz.wanandroid.ui.article

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.ArticleListBean
import com.shadowwingz.wanandroid.databinding.ArticleListItemBinding

class ArticleListAdapter :
    ListAdapter<ArticleListBean, ArticleListAdapter.ViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<ArticleListBean>() {
        override fun areItemsTheSame(oldItem: ArticleListBean, newItem: ArticleListBean): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ArticleListBean,
            newItem: ArticleListBean
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.article_list_item, parent, false)
        return ViewHolder(binding.root)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding: ArticleListItemBinding? = DataBindingUtil.getBinding(holder.itemView)
        binding?.data = getItem(position)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id.toLong()
    }
}