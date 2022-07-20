package com.example.albums.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class PhotosViewModelFactory(private val albumId: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PhotosViewModel::class.java)){
            return PhotosViewModel(albumId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
