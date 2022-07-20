package com.example.albums.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.albums.fragments.FragmentPhotosDirections
import com.example.albums.model.PhotosDataItem
import com.example.albums.databinding.RowItemsPhotosBinding


class PhotosAdapter(
    private val context: Context):
    RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    private var photosList: List<PhotosDataItem>? = null

    fun setPhotosList(photosList: List<PhotosDataItem>){
        this.photosList = photosList
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
            val currentPosition = photosList!![position]
            val url = GlideUrl(
                currentPosition.url, LazyHeaders.Builder()
                    .addHeader("User-Agent", "your-user-agent")
                    .build()
            )
            Glide.with(context).load(url).into(ivPhoto)
            holder.itemView.setOnClickListener {
                val directions = FragmentPhotosDirections
                    .actionFragmentPhotosToFragmentPhotoDetail(photosList!![position])
                it.findNavController().navigate(directions)
            }
        }
    }

    override fun getItemCount(): Int {
        return photosList!!.size
    }
}
