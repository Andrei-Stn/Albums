package com.example.albums.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.albums.Adapter.PhotosAdapter
import com.example.albums.Interface.ApiInterface
import com.example.albums.Model.PhotosDataItem
import com.example.albums.databinding.FragmentPhotosBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentPhotos : Fragment() {
    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!

    lateinit var photosAdapter: PhotosAdapter
    private lateinit var gridLayout: GridLayoutManager

    val args: FragmentPhotosArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotosBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.photosRv.hasFixedSize()
        gridLayout = GridLayoutManager(context, 3)
        binding.photosRv.layoutManager = gridLayout

        getMyPhotos()
    }

    private fun getMyPhotos(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getPhotos(args.photosId)

        retrofitData.enqueue(object : Callback<List<PhotosDataItem>?> {
            override fun onResponse(
                call: Call<List<PhotosDataItem>?>,
                response: Response<List<PhotosDataItem>?>
            ) {
                val responseBody = response.body()!!

                photosAdapter = PhotosAdapter(responseBody, context!!)
                photosAdapter.notifyDataSetChanged()
                binding.photosRv.adapter = photosAdapter
            }

            override fun onFailure(call: Call<List<PhotosDataItem>?>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT)
            }

        })
    }
}
