package com.dkgtech.news.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dkgtech.news.databinding.EverythingNewsRowBinding
import com.dkgtech.news.model.Article
import com.dkgtech.news.ui.NewsDetailsActivity
import com.squareup.picasso.Picasso

class RecyclerNewsAdapter(val context: Context, val arrNews: List<Article>) :
    RecyclerView.Adapter<RecyclerNewsAdapter.ViewHolder>() {
    class ViewHolder(val binding: EverythingNewsRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            EverythingNewsRowBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return arrNews.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            val data = arrNews[position]
            txtName.text = data.source.name
            txtPublishDate.text = data.publishedAt
            txtNewsHeadline.text = data.title
            txtAuthor.text = data.author
            Picasso.get().load(data.urlToImage).into(imgNews)

            ccView.setOnClickListener {
                context.startActivity(
                    Intent(context, NewsDetailsActivity::class.java)
                        .putExtra("NewsHeadline", data.title)
                        .putExtra("NewsDescription", data.content)
                        .putExtra("NewsName", data.source.name)
                        .putExtra("NewsPublishDate", data.publishedAt)
                        .putExtra("NewsImage", data.urlToImage)
                        .putExtra("NewsUrl", data.url)
                )
            }
        }
    }
}