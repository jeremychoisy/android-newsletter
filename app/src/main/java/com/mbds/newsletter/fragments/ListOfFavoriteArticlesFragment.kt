package com.mbds.newsletter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.MainActivity
import com.mbds.newsletter.R
import com.mbds.newsletter.adapters.ArticleAdapter
import com.mbds.newsletter.data.database.dao.ArticleDao
import com.mbds.newsletter.data.database.db.ArticleDatabase
import com.mbds.newsletter.data.models.Article
import com.mbds.newsletter.interfaces.ArticleCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 */
class ListOfFavoriteArticlesFragment : Fragment(), ArticleCallback {
    private lateinit var adapter: ArticleAdapter
    private lateinit var articleDAO: ArticleDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity != null) {
            val articleDB = ArticleDatabase.getInstance((activity as MainActivity).applicationContext)
            articleDAO = articleDB.articleDao()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleText: TextView = view.findViewById(R.id.category_title)
        val noArticleFoundText: TextView = view.findViewById(R.id.no_article_found)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        titleText.visibility = View.GONE
        noArticleFoundText.text = getString(R.string.no_favorite_article_found)
        adapter = ArticleAdapter(mutableListOf(), this)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
        articleDAO.getAll().observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                noArticleFoundText.visibility = View.GONE
            } else {
                noArticleFoundText.visibility = View.VISIBLE
            }
            setArticlesList(it)
        })
    }

    /**
     * Set the list of articles in the adapter
     */
    private fun setArticlesList(articles: List<Article>) {
        adapter.setArticles(articles)
    }

    override fun getFavoriteArticles(): LiveData<List<Article>> {
        return articleDAO.getAll()
    }

    override fun removeFavoriteArticle(article: Article) {
        lifecycleScope.launch(Dispatchers.IO) {
            articleDAO.delete(article)
        }
    }

    override fun onClick(article: Article) {
        (activity as? MainActivity)?.changeFragment(ArticleDetailsFragment.newInstance(article))
    }
}