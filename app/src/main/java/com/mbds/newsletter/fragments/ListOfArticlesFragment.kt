package com.mbds.newsletter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.MainActivity
import com.mbds.newsletter.R
import com.mbds.newsletter.adapters.ArticleAdapter
import com.mbds.newsletter.adapters.ArticleCallback
import com.mbds.newsletter.data.database.db.ArticleDatabase
import com.mbds.newsletter.data.models.Article
import com.mbds.newsletter.data.models.Resource
import com.mbds.newsletter.data.models.Status.*
import com.mbds.newsletter.data.services.ArticleHttpService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [ListOfArticlesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListOfArticlesFragment : Fragment(), ArticleCallback {
    private lateinit var category: String
    private val repository = ArticleHttpService()
    private lateinit var adapter: ArticleAdapter
    private lateinit var articleDB: ArticleDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity != null) {
            articleDB = ArticleDatabase.getInstance((activity as MainActivity).applicationContext)
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
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val spinner: ProgressBar = view.findViewById(R.id.spinner)
        titleText.text = category
        adapter = ArticleAdapter(mutableListOf(), this)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
        fetchData().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        spinner.visibility = View.GONE
                        resource.data?.let { articles -> setArticlesList(articles) }
                    }
                    ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        spinner.visibility = View.GONE
                    }
                    LOADING -> {
                        spinner.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun fetchData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getArticles(category)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    private fun setArticlesList(articles: List<Article>) {
        adapter.setArticles(articles)
    }

    override fun onClick(article: Article) {
        lifecycleScope.launch(Dispatchers.IO) {
            articleDB.articleDao().insert(article)
        }
    }

    companion object {
        fun newInstance(category: String): ListOfArticlesFragment {
            return ListOfArticlesFragment().apply {
                this.category = category
            }
        }
    }
}