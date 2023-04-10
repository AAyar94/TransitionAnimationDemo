package com.aayar94.transitiondemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.transitiondemo.databinding.RowLayoutRecyclerItemBinding
import com.bumptech.glide.Glide

class PhotosAdapter(
    val onItemClick: (imageLink: String, extra: FragmentNavigator.Extras) -> Unit
) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {
    private val imageList: MutableList<String> = mutableListOf()

    inner class PhotosViewHolder(private val binding: RowLayoutRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                ViewCompat.setTransitionName(rowImage, imageList[position])

                Glide.with(root.context)
                    .load(imageList[position])
                    .into(rowImage)
            }
            binding.rowImage.setOnClickListener {
                val transitionName = ViewCompat.getTransitionName(binding.rowImage)!!
                val extras = FragmentNavigatorExtras(
                    binding.rowImage to transitionName
                )
                onItemClick(imageList[position], extras)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val binding = RowLayoutRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PhotosViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setData(list: List<String>) {
        imageList.clear()
        imageList.addAll(list)
    }

}