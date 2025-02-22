package com.example.testtask.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.user.User
import com.example.testtask.databinding.ItemUserBinding

class UserViewHolder(
    private val binding: ItemUserBinding,
    private val itemClick: (User) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        with(binding) {
            userId.text = "ID: ${user.id}"
            userName.text = "Name: ${user.name}"
            userEmail.text = "Email: ${user.email}"
            root.setOnClickListener { itemClick(user) }
        }
    }
}