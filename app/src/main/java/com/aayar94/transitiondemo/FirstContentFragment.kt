package com.aayar94.transitiondemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.aayar94.transitiondemo.databinding.FragmentFirstContentBinding
import com.bumptech.glide.Glide


class FirstContentFragment : Fragment() {
    private var _binding: FragmentFirstContentBinding? = null
    private val binding get() = _binding!!
    private val adapter: PhotosAdapter by lazy {
        PhotosAdapter { link, extra ->
            val action =
                FirstContentFragmentDirections.actionFirstContentFragmentToSecondContentFragment(
                    link
                )
            findNavController().navigate(action, extra)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstContentBinding.inflate(layoutInflater, container, false)

        val string = "https://picsum.photos/200"
        var dataSet: MutableList<String> = mutableListOf()
        for (i in 0 until 20) {
            dataSet.add(string)
        }
        adapter.setData(dataSet)

        binding.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        return binding.root
    }


}