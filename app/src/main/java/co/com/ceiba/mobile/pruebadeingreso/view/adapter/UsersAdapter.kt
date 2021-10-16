package co.com.ceiba.mobile.pruebadeingreso.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.databinding.UserListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.domain.model.User
import javax.inject.Inject
import kotlin.properties.Delegates

class UsersAdapter @Inject constructor(
        private val onClick: (Long) -> Unit
) : RecyclerView.Adapter<UserViewHolder>() {

    var users: List<User> by Delegates.observable(emptyList()) { initialUsers, _, newUsers ->
        // Todo: We can have a comparison strategy for updating only new users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }
}