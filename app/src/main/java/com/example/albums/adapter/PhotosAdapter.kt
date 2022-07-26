package com.example.albums.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.albums.fragments.FragmentPhotosDirections
import com.example.albums.model.PhotosDataItem
import com.example.albums.databinding.RowItemsPhotosBinding


class PhotosAdapter(
    private val context: Context):
    ListAdapter<PhotosDataItem, PhotosAdapter.PhotosViewHolder>(PhotosDiffCallback()) {

    class PhotosDiffCallback: DiffUtil.ItemCallback<PhotosDataItem>(){
        override fun areItemsTheSame(oldItem: PhotosDataItem, newItem: PhotosDataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotosDataItem, newItem: PhotosDataItem): Boolean {
            return oldItem == newItem
        }
    }

    class PhotosViewHolder(val binding: RowItemsPhotosBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowItemsPhotosBinding.inflate(layoutInflater, parent, false)
        return  PhotosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.binding.apply {
            val currentPosition = getItem(position)
            val url = GlideUrl(
                currentPosition.url, LazyHeaders.Builder()
                    .addHeader("User-Agent", "your-user-agent")
                    .build()
            )
            Glide.with(context).load(url).into(ivPhoto)
            holder.itemView.setOnClickListener {
                val directions = FragmentPhotosDirections
                    .actionFragmentPhotosToFragmentPhotoDetail(currentPosition)
                it.findNavController().navigate(directions)
            }
        }
    }
}
