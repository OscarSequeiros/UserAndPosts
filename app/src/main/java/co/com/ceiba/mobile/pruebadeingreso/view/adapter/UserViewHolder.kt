package co.com.ceiba.mobile.pruebadeingreso.view.adapter

import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.databinding.UserListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.domain.model.User

class UserViewHolder(private val binding: UserListItemBinding, val onClick: (Long) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) = with(binding) {
        name.text = user.name
        phone.text = user.phoneNumber
        email.text = user.email
        btnViewPost.setOnClickListener {
            onClick(user.id)
        }
    }
}