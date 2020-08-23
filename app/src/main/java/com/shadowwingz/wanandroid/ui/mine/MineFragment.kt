package com.shadowwingz.wanandroid.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.ui.article.SubTitleItem
import com.shadowwingz.wanandroid.ui.wrapper.HeaderAndFootWrapper
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : Fragment() {

    private val dataList = listOf<SubTitleItem>(
        SubTitleItem.MY_COIN,
        SubTitleItem.MY_SHARE,
        SubTitleItem.MY_COLLECTION,
        SubTitleItem.READ_LATER,
        SubTitleItem.READ_RECORD,
        SubTitleItem.GITHUB,
        SubTitleItem.ABOUT,
        SubTitleItem.SETTINGS
    )

    private var mParent: ViewGroup? = null

    companion object {
        @JvmStatic
        fun newInstance() = MineFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mParent = container
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = MineAdapter(dataList)

        val profilePanelView: View =
            LayoutInflater.from(activity).inflate(R.layout.profile_panel, mParent, false)

        val headerAndFootWrapper = HeaderAndFootWrapper(adapter as Adapter<ViewHolder>)
        headerAndFootWrapper.addHeaderView(profilePanelView)

        rvMine.layoutManager = LinearLayoutManager(activity)
        rvMine.adapter = headerAndFootWrapper
    }

    class MineAdapter(val dataList: List<SubTitleItem>) :
        Adapter<MineAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_mine_list_item, parent, false)
            return ViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.ivIcon.setImageResource(dataList[position].icon)
            holder.tvSubTitle.text = dataList[position].subTitle
            holder.tvSubDesc.text = dataList[position].subDesc
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val ivIcon: ImageView = itemView.findViewById(R.id.ivIcon)
            val tvSubTitle: TextView = itemView.findViewById(R.id.tvSubTitle)
            val tvSubDesc: TextView = itemView.findViewById(R.id.tvSubDesc)
        }
    }
}