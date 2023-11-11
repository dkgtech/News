package com.dkgtech.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dkgtech.news.databinding.EverythingNewsRowBinding
import com.dkgtech.news.model.Article
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
        }
    }
}