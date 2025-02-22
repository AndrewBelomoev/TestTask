package com.example.testtask.presentation.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtask.databinding.FragmentUsersBinding
import com.example.testtask.presentation.adapter.UserAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val viewModel by viewModel<UsersViewModel>()
    private lateinit var userAdapter: UserAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUsersBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            userAdapter = UserAdapter { user ->
                findNavController().navigate(
                    directions = UsersFragmentDirections.actionUsersFragmentToUserDetailsFragment(
                        name = user.name ?: "",
                        email = user.email ?: "",
                        phone = user.phone ?: "",
                        city = user.address?.city ?: ""
                    )
                )
            }

            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = userAdapter
            }

            lifecycleScope.launch {
                viewModel.users.collect { users ->
                    userAdapter.submitList(users)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}