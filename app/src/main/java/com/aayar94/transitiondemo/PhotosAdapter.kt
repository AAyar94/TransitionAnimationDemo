package com.aayar94.transitiondemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.transitiondemo.databinding.RowLayoutRecyclerItemBinding
import com.bumptech.glide.Glide

class PhotosAdapter(val onItemClick: (imageLink: String,extra: FragmentNavigator.Extras) -> Unit) :
    RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    val imageList: MutableList<String> = mutableListOf()

    inner class PhotosViewHolder(private val binding: RowLayoutRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.rowImage.apply {
                transitionName = "imageT"
                    .apply {
                        Glide.with(binding.root.context)
                            .load(imageList[position])
                            .into(binding.rowImage)
                    }
            }
            binding.rowImage.setOnClickListener {
                val extra = FragmentNavigatorExtras(
                    binding.rowImage to "imageT"
                )
                onItemClick(imageList[position],extra)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val binding =
            RowLayoutRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotosViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setData(list: MutableList<String>) {
        imageList.clear()
        imageList.addAll(list)
    }

}