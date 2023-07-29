package com.shadowwingz.wanandroid.ui.question;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.Question
import kotlinx.android.synthetic.main.item_question.view.*

/**
 * created by shadowwingz on 2021-06-29 21:46
 */
class QuestionAdapter : ListAdapter<Question, QuestionAdapter.ViewHolder>(QuestionDiffCallback()) {
  
  class QuestionDiffCallback : DiffUtil.ItemCallback<Question>() {
    override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
      return oldItem.title == newItem.title
    }
    
    override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
      return oldItem == newItem
    }
  }
  
  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
    return ViewHolder(view)
  }
  
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val data = getItem(position)
    holder.itemView.apply {
      tvAuthor.text = data.author
      tvTag.text = data.tags[0].name
      tvNiceDate.text = data.niceDate
      tvTitle.text = data.title
      tvDesc.text = data.desc
      tvchapterName.text = data.superChapterName
    }
  }
}
