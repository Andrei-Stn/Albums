package com.example.albums.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.albums.Fragments.FragmentAlbumDirections
import com.example.albums.Model.AlbumDataItem
import com.example.albums.databinding.RowItemsAlbumsBinding

class AlbumAdapter(
    val albumList: List<AlbumDataItem>
) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    inner class AlbumViewHolder(val binding: RowItemsAlbumsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowItemsAlbumsBinding.inflate(layoutInflater, parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.binding.apply {

            val currentPosition = albumList[position]
            tvId.text = currentPosition.id.toString()
            tvTitle.text = currentPosition.title
        }
        holder.itemView.setOnClickListener {
            val directions =
                FragmentAlbumDirections.actionAlbumFragmentToFragmentPhotos(albumList[position].id)
            it.findNavController().navigate(directions)
        }
    }

    override fun getItemCount(): Int {
        return albumList.size
    }
}
