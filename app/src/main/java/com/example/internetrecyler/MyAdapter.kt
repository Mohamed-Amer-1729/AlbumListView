package com.example.internetrecyler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.internetrecyler.data.Post
import com.example.internetrecyler.databinding.AlbumLayoutBinding

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: AlbumLayoutBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<Post>(){
        override fun areItemsTheSame(p0: Post, p1: Post): Boolean {
            return p0.id == p1.id
        }

        override fun areContentsTheSame(p0: Post, p1: Post): Boolean {
            return p0 == p1
        }

    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    var posts: List<Post>
        get() = differ.currentList
        set(value) {differ.submitList(value)}

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder(AlbumLayoutBinding.inflate(
            LayoutInflater.from(p0.context),
            p0,
            false
        ))
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.binding.apply {
            val post = posts[p1]
            albumTitle.text = post.title
            userId.text = post.userId.toString()
        }
    }

}