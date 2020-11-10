package com.mbds.newsletter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.data.models.Article
import com.mbds.newsletter.helpers.formatDate

class ArticleDetailsFragment() : Fragment() {

    private lateinit var currentArticle: Article

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articleTitleText: TextView = view.findViewById(R.id.article_title)
        val articleImage: ImageView = view.findViewById(R.id.article_image)
        val articleDescriptionText: TextView = view.findViewById(R.id.article_desc)
        val articleContent: TextView = view.findViewById(R.id.article_content)
        val articleCreditsText: TextView = view.findViewById(R.id.article_credit)
        val articleUrl: TextView = view.findViewById(R.id.article_url)

        articleUrl.text = currentArticle.url
        articleTitleText.text = currentArticle.title
        Glide.with(view).load(currentArticle.urlToImage).into(articleImage)
        articleDescriptionText.text = currentArticle.description
        articleContent.text = currentArticle.content
        val date = formatDate(currentArticle.publishedAt)
        val creditText = "Published by "+ currentArticle.author +"the "+date+" in the "+ currentArticle.source.name
        articleCreditsText.text = creditText

    }

    companion object {
        fun newInstance(article: Article): ArticleDetailsFragment {
            return ArticleDetailsFragment().apply {
                this.currentArticle = article
            }
        }
    }
}