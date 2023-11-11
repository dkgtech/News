package com.dkgtech.news.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dkgtech.news.R
import com.dkgtech.news.databinding.ActivityNewsDetailsBinding
import com.squareup.picasso.Picasso

class NewsDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {

            val intent = intent
            val newsHeadline = intent.getStringExtra("NewsHeadline")
            val newsDescription = intent.getStringExtra("NewsDescription")
            val name = intent.getStringExtra("NewsName")
            val publishDate = intent.getStringExtra("NewsPublishDate")
            val newsImage = intent.getStringExtra("NewsImage")
            val url = intent.getStringExtra("NewsUrl")

            txtNewsHeadline.text = newsHeadline
            txtTitle.text = newsHeadline
            txtDescription.text = newsDescription
            txtName.text = name
            txtPublishDate.text = publishDate
            Picasso.get().load(newsImage).into(imgMain)

            imgBack.setOnClickListener {
                startActivity(Intent(this@NewsDetailsActivity, MainActivity::class.java))
            }

            cvViewImage.setOnClickListener {
                val iNext = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(iNext)
            }

        }
    }
}