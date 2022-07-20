package com.example.albums.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albums.model.PhotosDataItem
import com.example.albums.retrofit.ApiInterface
import com.example.albums.retrofit.RetroInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosViewModel(albumId: Int) : ViewModel() {

    private var _photosLiveData = MutableLiveData<List<PhotosDataItem>>()
    val photosLiveData: LiveData<List<PhotosDataItem>>
        get() = _photosLiveData

    init {
        getPhotosCall(albumId)
    }


    fun getPhotosCall(albumId: Int) {
        viewModelScope.launch {
            try {
                val retroInstance = RetroInstance.getRetroInstance()
                val retroService = retroInstance.create(ApiInterface::class.java)
                val call = retroService.getPhotos(albumId)
                call.enqueue(object : Callback<List<PhotosDataItem>> {
                    override fun onResponse(
                        call: Call<List<PhotosDataItem>>,
                        response: Response<List<PhotosDataItem>>
                    ) {
                        _photosLiveData.postValue(response.body())
                    }

                    override fun onFailure(call: Call<List<PhotosDataItem>>, t: Throwable) {
                        _photosLiveData.value = null
                    }

                })
            } catch (e: Exception) {

            }
        }
    }
}
