package com.mbds.newsletter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.data.models.Article
import com.mbds.newsletter.helpers.formatDate
import com.mbds.newsletter.interfaces.ArticleCallback

class ArticleAdapter(private val dataSet: MutableList<Article>, private val callback: ArticleCallback) :
        RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    inner class ViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {

        fun bind(item: Article) {
            val txtTitle = root.findViewById<TextView>(R.id.title)
            val img = root.findViewById<AppCompatImageView>(R.id.image)
            val txtDesc = root.findViewById<TextView>(R.id.desc)
            val txtAuthor = root.findViewById<TextView>(R.id.author)
            val txtPublishedAt = root.findViewById<TextView>(R.id.published_at)
            val favoriteButton = root.findViewById<ImageButton>(R.id.favorite)
            val favoriteArticlesListObservable = callback.getFavoriteArticles()
            val setFavoriteDrawable = root.context.getDrawable(R.drawable.ic_favorite_border_black_48dp)
            val unsetFavoriteDrawable = root.context.getDrawable(R.drawable.ic_favorite_black_48dp)

            txtTitle.text = item.title
            txtDesc.text = item.description
            txtAuthor.text = item.author
            txtPublishedAt.text = formatDate(item.publishedAt)
            Glide.with(root).load(item.urlToImage).into(img)
            favoriteButton.visibility = View.VISIBLE


            favoriteArticlesListObservable.observe(root.context as LifecycleOwner, {
                val isArticleFavorite = it.contains(item)
                favoriteButton.background = if (isArticleFavorite) unsetFavoriteDrawable else setFavoriteDrawable
            })

            favoriteButton.setOnClickListener {
                when(it.background) {
                    setFavoriteDrawable -> {callback.addFavoriteArticle(item)}
                    unsetFavoriteDrawable -> {callback.removeFavoriteArticle(item)}
                }
            }

            root.setOnClickListener {
                callback.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    fun setArticles(articles: List<Article>) {
        this.dataSet.apply{
            clear()
            addAll(articles)
        }
        notifyDataSetChanged()
    }
}