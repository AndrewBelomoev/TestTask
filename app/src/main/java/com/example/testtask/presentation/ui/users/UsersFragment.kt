package com.example.testtask.presentation.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.utills.NetworkUtils
import com.example.testtask.databinding.FragmentUsersBinding
import com.example.testtask.presentation.adapter.UserAdapter
import com.example.testtask.presentation.model.LceState
import com.google.android.material.snackbar.Snackbar
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
                        name = user.name ?: "No name",
                        email = user.email ?: " No email",
                        phone = user.phone ?: "No phone",
                        city = user.address?.city ?: "No city"
                    )
                )
            }

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.fetchUsers()
                progressBar.visibility = View.GONE
            }

            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = userAdapter
            }

            lifecycleScope.launch {
                viewModel.usersState.collect { state ->
                    when (state) {
                        is LceState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                            binding.errorText.visibility = View.GONE
                        }

                        is LceState.Content -> {
                            binding.progressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.VISIBLE
                            binding.errorText.visibility = View.GONE
                            binding.swipeRefreshLayout.isRefreshing = false
                            userAdapter.submitList(state.value)
                        }

                        is LceState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.GONE
                            binding.errorText.visibility = View.VISIBLE

                            binding.swipeRefreshLayout.isRefreshing = false
                            binding.errorText.text = "Something went wrong: ${state.throwable.message}"

                            if (NetworkUtils.isInternetAvailable(requireContext())) {
                                showSnackbar(state.throwable.localizedMessage ?: "")
                            } else {
                                showSnackbar("No internet connections")
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).apply {
            show()
        }.setAction("Reload") {
            viewModel.fetchUsers()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}