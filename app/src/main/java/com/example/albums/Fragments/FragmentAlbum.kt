package com.example.albums.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.albums.Adapter.AlbumAdapter
import com.example.albums.Interface.ApiInterface
import com.example.albums.Model.AlbumDataItem
import com.example.albums.databinding.FragmentAlbumsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL= "https://jsonplaceholder.typicode.com/"

class FragmentAlbum: Fragment() {
    private var _binding: FragmentAlbumsBinding? = null
    private val binding get() = _binding!!

    lateinit var albumAdapter: AlbumAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.albumRv.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this.context)
        binding.albumRv.layoutManager = linearLayoutManager

        getMyData()
    }

    private fun getMyData(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<AlbumDataItem>?> {
            override fun onResponse(
                call: Call<List<AlbumDataItem>?>,
                response: Response<List<AlbumDataItem>?>
            ) {
                val responseBody = response.body()!!

                albumAdapter = AlbumAdapter(responseBody)
                albumAdapter.notifyDataSetChanged()
                binding.albumRv.adapter = albumAdapter
            }
            override fun onFailure(call: Call<List<AlbumDataItem>?>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
