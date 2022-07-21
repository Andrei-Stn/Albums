package com.example.albums.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.albums.adapter.AlbumAdapter
import com.example.albums.databinding.FragmentAlbumsBinding
import com.example.albums.viewModel.AlbumViewModel
import kotlinx.android.synthetic.main.fragment_albums.*

class FragmentAlbum: Fragment() {
    private var _binding: FragmentAlbumsBinding? = null
    private val binding get() = _binding!!

    lateinit var albumAdapter: AlbumAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var viewModel: AlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)

        initRecycler()

        getAlbum()

        binding.swiperefresh.setOnRefreshListener {
            getAlbum()
            swiperefresh.isRefreshing = false
        }

        return binding.root
    }

    private fun initRecycler(){
        linearLayoutManager = LinearLayoutManager(this.context)
        binding.albumRv.layoutManager = linearLayoutManager
        albumAdapter = AlbumAdapter()
    }

    private fun getAlbum(){
        viewModel.albumsLiveData.observe(viewLifecycleOwner) {
            if(it != null){
                albumAdapter.setAlbumList(it)
                albumAdapter.notifyDataSetChanged()
                binding.albumRv.adapter = albumAdapter
            }else{
                Toast.makeText(requireContext(), "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.getAlbumsCall()
    }
}
