package com.example.albums.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.albums.fragments.FragmentAlbumDirections
import com.example.albums.model.AlbumDataItem
import com.example.albums.databinding.RowItemsAlbumsBinding

class AlbumAdapter : ListAdapter<AlbumDataItem, AlbumAdapter.AlbumViewHolder>(AlbumDataDiffCallback()) {

    class AlbumDataDiffCallback: DiffUtil.ItemCallback<AlbumDataItem>(){
        override fun areItemsTheSame(oldItem: AlbumDataItem, newItem: AlbumDataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AlbumDataItem, newItem: AlbumDataItem): Boolean {
            return oldItem == newItem
        }
    }

    class AlbumViewHolder(val binding: RowItemsAlbumsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowItemsAlbumsBinding.inflate(layoutInflater, parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {

            val currentPosition = getItem(position)
            tvId.text = currentPosition.id.toString()
            tvTitle.text = currentPosition.title
        }
        holder.itemView.setOnClickListener {
            val directions =
                FragmentAlbumDirections.actionAlbumFragmentToFragmentPhotos(item.id)
            it.findNavController().navigate(directions)
        }
    }
}
