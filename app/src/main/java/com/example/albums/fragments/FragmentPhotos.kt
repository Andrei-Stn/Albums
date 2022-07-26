package com.example.albums.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.albums.adapter.PhotosAdapter
import com.example.albums.databinding.FragmentPhotosBinding
import com.example.albums.viewModel.PhotosViewModel
import com.example.albums.viewModel.PhotosViewModelFactory
import kotlinx.android.synthetic.main.fragment_albums.*

const val GRID_SPAN = 3

class FragmentPhotos : Fragment() {
    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!

    lateinit var photosAdapter: PhotosAdapter
    private lateinit var gridLayout: GridLayoutManager

    private lateinit var viewModel: PhotosViewModel
    private lateinit var viewModelFactory: PhotosViewModelFactory


    private val args: FragmentPhotosArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotosBinding.inflate(inflater, container, false)

        viewModelFactory = PhotosViewModelFactory(args.photosId)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(PhotosViewModel::class.java)

        initRecycler()

        getPhotos(args.photosId)

        binding.swiperefresh.setOnRefreshListener {
            getPhotos(args.photosId)
            swiperefresh.isRefreshing = false
        }

        return binding.root
    }

    private fun initRecycler(){
        gridLayout = GridLayoutManager(context, GRID_SPAN)
        binding.photosRv.layoutManager = gridLayout
        photosAdapter = PhotosAdapter(requireContext())
    }

    private fun getPhotos(albumId: Int){
        viewModel.photosLiveData.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                photosAdapter.submitList(it)
                binding.photosRv.adapter = photosAdapter
            } else{
                Toast.makeText(requireContext(), "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.getPhotosCall(albumId)
    }
}
