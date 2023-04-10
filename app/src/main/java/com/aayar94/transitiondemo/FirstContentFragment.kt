package com.aayar94.transitiondemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aayar94.transitiondemo.databinding.FragmentFirstContentBinding
import com.google.android.material.transition.MaterialElevationScale


class FirstContentFragment : Fragment() {
    private var _binding: FragmentFirstContentBinding? = null
    private val binding get() = _binding!!
    private val adapter: PhotosAdapter by lazy {
        PhotosAdapter { link, extra ->
            exitTransition = MaterialElevationScale(false).apply {
                duration = 500
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = 500
            }
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        initAdapter()
    }

    private fun initAdapter() {
        val string1 =
            "https://plus.unsplash.com/premium_photo-1673254850680-969be808b314?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
        val string2 =
            "https://images.unsplash.com/photo-1680443285773-ef42672d00da?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
        val string3 =
            "https://images.unsplash.com/photo-1680770600914-c69d380e2405?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=928&q=80"

        val items = List(20) {
            when (it % 3) {
                0 -> string1
                1 -> string2
                else -> string3
            }
        }
        binding.recyclerView.adapter = adapter.also { it.setData(items) }
    }
}