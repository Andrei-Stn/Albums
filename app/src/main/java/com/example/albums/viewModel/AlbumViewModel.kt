package com.example.albums.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albums.model.AlbumDataItem
import com.example.albums.retrofit.ApiInterface
import com.example.albums.retrofit.RetroInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class AlbumViewModel: ViewModel() {

    private var _albumsLiveData = MutableLiveData<List<AlbumDataItem>>()
    val albumsLiveData: LiveData<List<AlbumDataItem>>
        get() = _albumsLiveData

    init{
        getAlbumsCall()
    }

    fun getAlbumsCall(){
        viewModelScope.launch {
            try{
                val retroInstance = RetroInstance.getRetroInstance()
                val retroService = retroInstance.create(ApiInterface::class.java)
                val call = retroService.getAlbums()
                call.enqueue(object : Callback<List<AlbumDataItem>>{
                    override fun onResponse(
                        call: Call<List<AlbumDataItem>>,
                        response: Response<List<AlbumDataItem>>
                    ) {
                        _albumsLiveData.postValue(response.body())
                    }
                    override fun onFailure(call: Call<List<AlbumDataItem>>, t: Throwable) {
                        _albumsLiveData.value = null
                    }
                })
            } catch (e: Exception){

            }
        }
    }
}
