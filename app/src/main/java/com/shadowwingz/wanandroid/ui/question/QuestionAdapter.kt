package com.shadowwingz.wanandroid.ui.question;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.Question
import com.shadowwingz.wanandroid.databinding.ItemQuestionBinding

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
    val binding = DataBindingUtil.inflate<ViewDataBinding>(
      LayoutInflater.from(parent.context), R.layout.item_question, parent, false
    )
    return ViewHolder(binding.root)
  }
  
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val binding: ItemQuestionBinding? = DataBindingUtil.getBinding(holder.itemView)
    binding?.data = getItem(position)
  }
}
