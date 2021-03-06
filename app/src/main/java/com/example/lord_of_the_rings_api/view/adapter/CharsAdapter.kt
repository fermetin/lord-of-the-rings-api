package com.example.lord_of_the_rings_api.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lord_of_the_rings_api.R
import com.example.lord_of_the_rings_api.databinding.CharsRecyclerViewItemBinding
import com.example.lord_of_the_rings_api.service.model.Character

class CharsAdapter(private val charactersList: List<Character>) : RecyclerView.Adapter<CharsAdapter.CharsViewHolder>() {


    inner class CharsViewHolder(
        val charsRecyclerViewItemBinding: CharsRecyclerViewItemBinding
    ) :RecyclerView.ViewHolder(charsRecyclerViewItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.chars_recycler_view_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CharsViewHolder, position: Int) {
        val currentItem : Character =  charactersList[position]
        holder.charsRecyclerViewItemBinding.character = currentItem
        holder.itemView.setOnClickListener {
            val bundle = bundleOf("selectedChar" to currentItem)
            it.findNavController().navigate(R.id.action_charsListFragmentNav_to_charsDetailsFragment,bundle)
        }
    }

    override fun getItemCount() = charactersList.size

}
