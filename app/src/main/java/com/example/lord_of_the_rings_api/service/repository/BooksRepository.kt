package com.example.lord_of_the_rings_api.service.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lord_of_the_rings_api.service.model.ApiResBook
import com.example.lord_of_the_rings_api.service.model.Book
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksRepository :BaseRepository(){

    fun getBooksList():LiveData<List<Book>>{
        var data = MutableLiveData<List<Book>>()

        webService.getAllBooksFromApi().enqueue(object :Callback<ApiResBook>{
            override fun onFailure(call: Call<ApiResBook>, t: Throwable) {
                Log.e("Something Went Wrong",t.toString())
            }
            override fun onResponse(call: Call<ApiResBook>, response: Response<ApiResBook>) {
                if (response.isSuccessful){
                    data.value = response.body()?.docs
                }
            }

        })
        return data
    }

}