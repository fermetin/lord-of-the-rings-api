package com.example.lord_of_the_rings_api.view.ui.BooksActivity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lord_of_the_rings_api.R
import com.example.lord_of_the_rings_api.service.model.Book
import com.example.lord_of_the_rings_api.view.BaseActivity
import com.example.lord_of_the_rings_api.view.adapter.denemeAdapter
import com.example.lord_of_the_rings_api.viewModel.BooksListViewModel
import kotlinx.android.synthetic.main.activity_books.*

class BooksActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        val booksListViewModel = ViewModelProvider(this).get(BooksListViewModel::class.java)
        observeViewModel(booksListViewModel)
    }
    private fun observeViewModel(viewModel: BooksListViewModel){
        viewModel.getBooksListObservable().observe(this, object : Observer<List<Book>>{
            override fun onChanged(books: List<Book>?) {
                books?.let{
                    Log.e("Results",it.toString())
                   booksRcy.also {
                       it.layoutManager = LinearLayoutManager(baseContext)
                       it.setHasFixedSize(true)
                       it.adapter = denemeAdapter(books)
                   }
                }
            }

        })
    }
}