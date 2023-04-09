package com.aayar94.transitiondemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.aayar94.transitiondemo.databinding.FragmentFirstContentBinding
import com.aayar94.transitiondemo.databinding.FragmentSecondContentBinding
import com.bumptech.glide.Glide


class SecondContentFragment : Fragment() {
    private var _binding: FragmentSecondContentBinding? = null
    private val binding get() = _binding!!
    val args: SecondContentFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondContentBinding.inflate(layoutInflater, container, false)

        Glide.with(binding.imageSecond.context)
            .load(args.link)
            .into(binding.imageSecond)

        binding.buttonB.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }

}