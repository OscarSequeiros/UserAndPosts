package co.com.ceiba.mobile.pruebadeingreso.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.databinding.PostListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.databinding.UserListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.domain.model.Post
import co.com.ceiba.mobile.pruebadeingreso.domain.model.User
import javax.inject.Inject
import kotlin.properties.Delegates

class PostsAdapter(
        private val posts: List<Post>
) : RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}