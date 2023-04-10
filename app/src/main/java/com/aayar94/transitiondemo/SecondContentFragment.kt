package com.aayar94.transitiondemo

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.ChangeImageTransform
import androidx.transition.ChangeTransform
import androidx.transition.TransitionInflater
import com.aayar94.transitiondemo.databinding.FragmentSecondContentBinding
import com.bumptech.glide.Glide
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale


class SecondContentFragment : Fragment() {
    private var _binding: FragmentSecondContentBinding? = null
    private val binding get() = _binding!!
    private val args: SecondContentFragmentArgs by navArgs()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.fragmentContainerView
            duration = 500
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondContentBinding.inflate(layoutInflater, container, false)
        ViewCompat.setTransitionName(binding.imageSecond, args.link)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageLink = args.link
        Glide.with(requireContext())
            .load(imageLink)
            .into(binding.imageSecond)

        binding.buttonB.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}