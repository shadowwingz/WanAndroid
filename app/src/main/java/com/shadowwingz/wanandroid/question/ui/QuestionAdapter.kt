package com.shadowwingz.wanandroid.question.ui;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.databinding.ItemQuestionBinding

/**
 * created by shadowwingz on 2021-07-10 22:19
 */
class QuestionAdapter : PagingDataAdapter<QuestionUiModel, QuestionAdapter.ViewHolder>(QUESTION_COMPARATOR) {

  companion object {
    private val QUESTION_COMPARATOR = object : DiffUtil.ItemCallback<QuestionUiModel>() {
      override fun areItemsTheSame(oldItem: QuestionUiModel, newItem: QuestionUiModel): Boolean {
        return oldItem.title == newItem.title
      }

      override fun areContentsTheSame(oldItem: QuestionUiModel, newItem: QuestionUiModel): Boolean {
        return oldItem == newItem
      }
    }
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val binding = ItemQuestionBinding.bind(holder.itemView)
    getItem(position)?.let {
      QuestionItemBinder.bind(binding, it)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
    return ViewHolder(view)
  }
}
