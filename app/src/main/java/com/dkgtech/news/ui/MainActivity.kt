package com.dkgtech.news.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dkgtech.news.API.NewsApiInterface
import com.dkgtech.news.adapter.RecyclerNewsAdapter
import com.dkgtech.news.databinding.ActivityMainBinding
import com.dkgtech.news.model.NewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {

            fetchNews("technology")
            searchNews()

        }
    }

    private fun searchNews() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    fetchNews(query)
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
               return true
            }

        })
    }

    private fun fetchNews(searchQuery: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiInterface::class.java)

        val response = retrofit.getEverythingNews(searchQuery, "b379a8cb98154c3f871919ff5d66cad5")

        response.enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    with(binding) {
                        rcViewNews.layoutManager = LinearLayoutManager(this@MainActivity)
                        rcViewNews.adapter =
                            RecyclerNewsAdapter(this@MainActivity, arrNews = responseBody.articles)
                    }
                }
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
}