package com.wahyu.myfootball.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.wahyu.myfootball.R
import com.wahyu.myfootball.databinding.ActivityMainBinding
import com.wahyu.myfootball.databinding.TabLayoutBinding
import com.wahyu.myfootball.ui.adapter.TabPagerAdapter
import com.wahyu.myfootball.ui.home.HomeFragment
import com.wahyu.myfootball.ui.standing.StandingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
        reduceDragSensitivity()
    }

    private fun setupAdapter(){
        val adapter = TabPagerAdapter(
            this,
            arrayListOf(
                HomeFragment(),
                StandingFragment()
            )
        )

        binding.pager.adapter = adapter
        TabLayoutMediator(binding.tab, binding.pager) { tab, position ->
            when (position) {
                0 -> { tab.customView = getTabLayout("Home", R.drawable.ic_baseline_home_24) }
                1 -> { tab.customView = getTabLayout("Standing", R.drawable.ic_baseline_insert_chart_24) }
            }
        }.attach()
        binding.pager.setCurrentItem(0, true)
    }

    private fun getTabLayout(title: String, icon: Int): View? {
        val tabBinding = TabLayoutBinding.inflate(layoutInflater)
        tabBinding.title.text = title
        tabBinding.icon.setImageResource(icon)
        return tabBinding.root
    }

    private fun reduceDragSensitivity() {
        try {
            val ff =
                ViewPager2::class.java.getDeclaredField("mRecyclerView")
            ff.isAccessible = true
            val recyclerView = ff[binding.pager] as RecyclerView
            val touchSlopField =
                RecyclerView::class.java.getDeclaredField("mTouchSlop")
            touchSlopField.isAccessible = true
            val touchSlop = touchSlopField[recyclerView] as Int
            touchSlopField[recyclerView] = touchSlop * 4
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
}