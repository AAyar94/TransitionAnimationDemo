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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstContentBinding.inflate(layoutInflater, container, false)

        val string = "https://picsum.photos/200"

        Glide.with(binding.root.context)
            .load(string)
            .into(binding.imageFirst)

        //binding.imageFirst.setImageResource(R.drawable.baseline_android_24)

        binding.buttonA.setOnClickListener {
            val action =
                FirstContentFragmentDirections.actionFirstContentFragmentToSecondContentFragment(
                    string
                )
            val extra = FragmentNavigatorExtras(
                binding.imageFirst to "imageT"
            )
            findNavController().navigate(action, extra)
        }

        return binding.root
    }


}