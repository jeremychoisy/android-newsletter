package com.mbds.newsletter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.MainActivity
import com.mbds.newsletter.R
import com.mbds.newsletter.adapters.ArticleAdapter
import com.mbds.newsletter.data.database.dao.ArticleDao
import com.mbds.newsletter.adapters.SelectedFilter
import com.mbds.newsletter.data.database.db.ArticleDatabase
import com.mbds.newsletter.data.models.Article
import com.mbds.newsletter.data.models.Resource
import com.mbds.newsletter.data.models.Status
import com.mbds.newsletter.data.services.ArticleHttpService
import com.mbds.newsletter.interfaces.ArticleCallback
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
        val spinner: ProgressBar = view.findViewById(R.id.spinner)
        titleText.text = "Recherche"
        noArticleFoundText.text = getString(R.string.no_article_found)
        adapter = ArticleAdapter(mutableListOf(),this)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
        fetchData(createURL(false), createURL(true)).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
//                        recyclerView.visibility = View.VISIBLE
//                        spinner.visibility = View.GONE
                        println(resource.data)
                        resource.data?.let { articles -> if(articles.isNotEmpty())
                            setArticlesList(articles)
                        else
                            titleText.text = "Aucun résultat"}
                    }
                    Status.ERROR -> {
//                        recyclerView.visibility = View.VISIBLE
//                        spinner.visibility = View.GONE
                    }
                    Status.LOADING -> {
//                        spinner.visibility = View.VISIBLE
//                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
//        fetchData().observe(viewLifecycleOwner, Observer {
//            it?.let { resource ->
//                when (resource.status) {
//                    SUCCESS -> {
//                        recyclerView.visibility = View.VISIBLE
//                        spinner.visibility = View.GONE
//                        resource.data?.let { articles -> setArticlesList(articles) }
//                    }
//                    ERROR -> {
//                        recyclerView.visibility = View.VISIBLE
//                        spinner.visibility = View.GONE
//                    }
//                    LOADING -> {
//                        spinner.visibility = View.VISIBLE
//                        recyclerView.visibility = View.GONE
//                    }
//                }
//            }
//        })
    }
//
//    private fun fetchData() = liveData(Dispatchers.IO) {
//        emit(Resource.loading(data = null))
//        try {
//            emit(Resource.success(data = repository.getArticles(category)))
//        } catch (exception: Exception) {
//            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }

    override fun getFavoriteArticles(): LiveData<List<Article>> {
        return articleDAO.getAll()
    }

    override fun addFavoriteArticle(article: Article) {
        lifecycleScope.launch(Dispatchers.IO) {
            articleDAO.insert(article)
        }
    }

    override fun removeFavoriteArticle(article: Article) {
        lifecycleScope.launch(Dispatchers.IO) {
            articleDAO.delete(article)
        }
    }

    private fun setArticlesList(articles: List<Article>) {
        adapter.setArticles(articles)
    }

    companion object {
        fun newInstance(): ListOfArticlesFragment {
            return ListOfArticlesFragment().apply {
            }
        }
    }

    fun createURL(select: Boolean): String {
        var url = ""
        if(select){
            SelectedFilter.list.forEachIndexed { index, s ->
                url += s.replace(" ", "-")
                if(index < SelectedFilter.list.size-1)
                    url += ","
            }
        }
        else{
            SelectedFilter.listCategoryAndCountry.forEachIndexed { index, s ->
                url += s
                if(index < SelectedFilter.listCategoryAndCountry.size-1)
                    url += " AND "
            }
        }
        return url
    }

    private fun fetchData(category: String, sources: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getArticlesFiltered(category, sources)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

   override fun onClick(article: Article) {
        (activity as? MainActivity)?.changeFragment(ArticleDetailsFragment.newInstance(article))
    }
}