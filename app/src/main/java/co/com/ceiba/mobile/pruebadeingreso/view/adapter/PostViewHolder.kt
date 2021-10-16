package co.com.ceiba.mobile.pruebadeingreso.view.adapter

import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.databinding.PostListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.domain.model.Post

class PostViewHolder(private val binding: PostListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) = with(binding) {
        title.text = post.title
        body.text = post.body
    }
}