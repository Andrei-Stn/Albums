package com.example.albums.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.albums.Model.AlbumDataItem
import com.example.albums.databinding.RowItemsBinding

class AlbumAdapter(val albumList: List<AlbumDataItem>) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    inner class AlbumViewHolder(val binding: RowItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowItemsBinding.inflate(layoutInflater, parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.binding.apply {
            tvId.text = albumList[position].id.toString()
            tvTitle.text = albumList[position].title
        }
    }

    override fun getItemCount(): Int {
        return albumList.size
    }
}
