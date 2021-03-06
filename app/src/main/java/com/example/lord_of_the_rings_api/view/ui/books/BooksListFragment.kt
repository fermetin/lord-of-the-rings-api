package com.example.lord_of_the_rings_api.view.ui.books

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lord_of_the_rings_api.R
import com.example.lord_of_the_rings_api.service.model.Book
import com.example.lord_of_the_rings_api.view.adapter.BooksAdapter
import com.example.lord_of_the_rings_api.viewModel.books.BooksListViewModel
import kotlinx.android.synthetic.main.books_list_fragment.*
import timber.log.Timber

class BooksListFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.books_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Timber.i("ViewModelProvider Created")
        val booksListViewModel = ViewModelProvider(this).get(BooksListViewModel::class.java)

        observeViewModel(booksListViewModel)
    }

    private fun observeViewModel(viewModel: BooksListViewModel){
        viewModel.getBooksListObservable().observe(viewLifecycleOwner, object : Observer<List<Book>> {
            override fun onChanged(books: List<Book>?) {
                books?.let{
                    progressBarBookList.visibility = View.GONE
                    Log.e("Results",it.toString())
                    booksRecyclerView.also {
                        it.layoutManager = LinearLayoutManager(context)
                        it.setHasFixedSize(true)
                        it.adapter = BooksAdapter(books)
                    }
                }
            }
        })
    }


}