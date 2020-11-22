package com.mbds.newsletter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.R
import com.mbds.newsletter.adapters.CategoriesAdapter
import com.mbds.newsletter.adapters.EditorAdapter
import com.mbds.newsletter.data.models.Article
import com.mbds.newsletter.data.models.Editor
import com.mbds.newsletter.data.models.Resource
import com.mbds.newsletter.data.models.Status
import com.mbds.newsletter.data.services.EditorHttpService
import kotlinx.coroutines.Dispatchers

/**
 * A simple [Fragment] subclass.
 */
class EditorsFragment : Fragment(), EditorAdapter.EditorCallback {
    lateinit var recyclerView: RecyclerView

    private val repository = EditorHttpService()
    private lateinit var adapter: EditorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_editor)
        val spinner: ProgressBar = view.findViewById(R.id.spinner)
        adapter = EditorAdapter(mutableListOf(),this)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
        fetchData().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        spinner.visibility = View.GONE
                        println(resource.data)
                        resource.data?.let { editor -> setEditorsList(editor) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        spinner.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        spinner.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setEditorsList(editors: List<Editor>) {
        adapter.setEditors(editors)
    }

    override fun onClick(sourceName: String) {
        println(sourceName)
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