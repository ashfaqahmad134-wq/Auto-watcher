package com.autoviwer.watcher.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.autoviwer.watcher.R
import com.autoviwer.watcher.databinding.ActivityMainBinding
import com.autoviwer.watcher.ui.fragments.*
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupViewPager()
        setupTabs()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Auto Watcher"
    }

    private fun setupViewPager() {
        viewPager = binding.viewPager
        tabLayout = binding.tabLayout
        
        val adapter = PagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }

    private fun setupTabs() {
        tabLayout.setupWithViewPager(viewPager)
        
        // YouTube
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_youtube)
        // Instagram
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_instagram)
        // Facebook
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_facebook)
        // TikTok
        tabLayout.getTabAt(3)?.setIcon(R.drawable.ic_tiktok)
        // Twitter
        tabLayout.getTabAt(4)?.setIcon(R.drawable.ic_twitter)
        // LinkedIn
        tabLayout.getTabAt(5)?.setIcon(R.drawable.ic_linkedin)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // Settings
                true
            }
            R.id.action_logs -> {
                // View activity logs
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private inner class PagerAdapter(fm: androidx.fragment.app.FragmentManager) : 
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        
        private val fragments = listOf(
            YouTubeFragment(),
            InstagramFragment(),
            FacebookFragment(),
            TikTokFragment(),
            TwitterFragment(),
            LinkedInFragment()
        )
        
        private val titles = listOf("YouTube", "Instagram", "Facebook", "TikTok", "Twitter", "LinkedIn")
        
        override fun getCount() = fragments.size
        
        override fun getItem(position: Int) = fragments[position]
        
        override fun getPageTitle(position: Int) = titles[position]
    }
}
