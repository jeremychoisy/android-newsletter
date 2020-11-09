package com.mbds.newsletter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.MainActivity
import com.mbds.newsletter.R
import com.mbds.newsletter.adapters.CategoriesAdapter
import com.mbds.newsletter.data.models.Category
import com.mbds.newsletter.data.models.Resource
import com.mbds.newsletter.data.services.ArticleHttpService
import com.mbds.newsletter.data.services.EditorHttpService
import kotlinx.coroutines.Dispatchers

/**
 * A simple [Fragment] subclass.
 */
class EditorsFragment : Fragment(), CategoriesAdapter.CategoryCallback {
    lateinit var recyclerView: RecyclerView

    private val repository = EditorHttpService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onClick(categoryName: String) {
        TODO("Not yet implemented")
    }

    private fun fetchData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getEditors()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


}