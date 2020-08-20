package com.shadowwingz.wanandroid.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.shadowwingz.wanandroid.R
import kotlinx.android.synthetic.main.activity_container.*
import kotlinx.android.synthetic.main.simple_pager_title_layout.view.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView

class ContainerActivity : AppCompatActivity() {

    private val mDataList: List<String> = listOf("首页", "问答", "体系", "我的")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        viewPager.adapter = ExamplePagerAdapter(mDataList)
        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return mDataList.size
            }

            override fun getTitleView(context: Context?, index: Int): IPagerTitleView? {
                return CommonPagerTitleView(context).apply {
                    val customLayout: View =
                        LayoutInflater.from(context)
                            .inflate(R.layout.simple_pager_title_layout, null)
                    customLayout.apply {
                        tvTitle.text = mDataList[index]
                        ivTitle.setImageResource(R.mipmap.ic_launcher)
                    }
                    setContentView(customLayout)
                    onPagerTitleChangeListener =
                        object : CommonPagerTitleView.OnPagerTitleChangeListener {
                            override fun onDeselected(index: Int, totalCount: Int) {
                                tvTitle.setTextColor(Color.GRAY)
                            }

                            override fun onSelected(index: Int, totalCount: Int) {
                                tvTitle.setTextColor(Color.BLUE)
                            }

                            override fun onLeave(
                                index: Int,
                                totalCount: Int,
                                leavePercent: Float,
                                leftToRight: Boolean
                            ) {
                            }

                            override fun onEnter(
                                index: Int,
                                totalCount: Int,
                                enterPercent: Float,
                                leftToRight: Boolean
                            ) {
                            }

                        }
                    setOnClickListener { viewPager.currentItem = index }
                }
            }

            override fun getIndicator(context: Context?): IPagerIndicator? {
                return null
            }
        }
        magicIndicator.navigator = commonNavigator
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }
}