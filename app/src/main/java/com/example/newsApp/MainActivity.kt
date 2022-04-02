package com.example.newsApp

import android.net.Uri
import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsApp.api.RetrofitInstance
import com.example.newsApp.databinding.ActivityMainBinding
import com.example.newsApp.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), NewsItemClicked {
    private lateinit var mAdapter: NewsListAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rc = binding.recyclerView
        rc.layoutManager = LinearLayoutManager(this)
        fetchDataRetrofit()
        mAdapter = NewsListAdapter(this)
        rc.adapter = mAdapter
    }

    private fun fetchDataRetrofit() {
        val pBar = binding.progressBar
        pBar.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.IO) {
            val news = RetrofitInstance.newsApi.getHeadline()
            withContext(Dispatchers.Main) {
                mAdapter.updateNews(news.articles)
            }
            pBar.visibility = View.GONE

        }
    }


    override fun onItemClicked(item: News) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this,Uri.parse(item.url))

    }
}