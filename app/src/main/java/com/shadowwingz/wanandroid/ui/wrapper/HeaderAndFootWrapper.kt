package com.shadowwingz.wanandroid.ui.wrapper

import android.view.View
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class HeaderAndFootWrapper(adapter: Adapter<RecyclerView.ViewHolder>) :
  Adapter<RecyclerView.ViewHolder>() {

  companion object {
    private const val BASE_HEADER_VIEW_TYPE = 10000;
    private const val BASE_FOOTER_VIEW_TYPE = 20000;
  }

  private var mInnerAdapter = adapter
  private var mHeaderViews: SparseArrayCompat<View> = SparseArrayCompat<View>()
  private var mFooterViews: SparseArrayCompat<View> = SparseArrayCompat<View>()

  private fun getHeadersCount(): Int {
    return mHeaderViews.size()
  }

  private fun getFooterCount(): Int {
    return mFooterViews.size()
  }

  private fun getRealItemCount(): Int {
    return mInnerAdapter.itemCount
  }

  fun addHeaderView(view: View) {
    mHeaderViews.put(BASE_HEADER_VIEW_TYPE + getHeadersCount(), view)
  }

  fun addFooterView(view: View) {
    mFooterViews.put(BASE_FOOTER_VIEW_TYPE + getFooterCount(), view)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    mHeaderViews.get(viewType)?.let {
      return ViewHolder.createViewHolder(parent.context, it)
    }
    mFooterViews.get(viewType)?.let {
      return ViewHolder.createViewHolder(parent.context, it)
    }
    return mInnerAdapter.onCreateViewHolder(parent, viewType)
  }

  override fun getItemCount(): Int {
    return getHeadersCount() + getFooterCount() + getRealItemCount()
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (isHeaderViewPos(position)) return
    if (isFooterViewPos(position)) return
    mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount())
  }

  private fun isHeaderViewPos(position: Int): Boolean {
    return position < getHeadersCount()
  }

  private fun isFooterViewPos(position: Int): Boolean {
    return position >= getHeadersCount() + getRealItemCount()
  }

  override fun getItemViewType(position: Int): Int {
    if (isHeaderViewPos(position)) {
      return mHeaderViews.keyAt(position)
    } else if (isFooterViewPos(position)) {
      return mFooterViews.keyAt(position - getHeadersCount() - getRealItemCount())
    }
    return mInnerAdapter.getItemViewType(position - getHeadersCount())
  }
}