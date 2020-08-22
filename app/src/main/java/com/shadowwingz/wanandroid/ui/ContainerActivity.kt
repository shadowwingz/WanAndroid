package com.shadowwingz.wanandroid.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.ui.home.HomeFragment
import com.shadowwingz.wanandroid.ui.mine.MineFragment
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
    private val mIconList: List<Int> = listOf(
        R.drawable.ic_bottom_bar_home,
        R.drawable.ic_bottom_bar_wechat,
        R.drawable.ic_bottom_bar_navi,
        R.drawable.ic_bottom_bar_mine
    )

    private val mFragments: List<Fragment> = listOf(
        HomeFragment.newInstance(),
        HomeFragment.newInstance(),
        HomeFragment.newInstance(),
        MineFragment.newInstance()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        viewPager.adapter = TabFragmentPagerAdapter(
            supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            mFragments
        )
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
                        ivTitle.setImageResource(mIconList[index])
                    }
                    setContentView(customLayout)
                    val selectedColor = ContextCompat.getColor(
                        this@ContainerActivity,
                        R.color.bottom_nav_selected
                    )
                    val unselectedColor = ContextCompat.getColor(
                        this@ContainerActivity,
                        R.color.bottom_nav_unselected
                    )
                    onPagerTitleChangeListener =
                        object : CommonPagerTitleView.OnPagerTitleChangeListener {
                            override fun onDeselected(index: Int, totalCount: Int) {
                                tvTitle.setTextColor(unselectedColor)
                                ivTitle.setColorFilter(resources.getColor(R.color.bottom_nav_unselected))
                            }

                            override fun onSelected(index: Int, totalCount: Int) {
                                tvTitle.setTextColor(selectedColor)
                                ivTitle.setColorFilter(resources.getColor(R.color.bottom_nav_selected))
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