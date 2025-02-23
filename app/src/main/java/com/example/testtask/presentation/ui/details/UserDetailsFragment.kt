package com.example.testtask.presentation.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.testtask.databinding.FragmentUserDetailsBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val viewModel by viewModel<UserDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserDetailsBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: UserDetailsFragmentArgs by navArgs()
        viewModel.setUserDetails(args.id,args.name, args.email, args.phone, args.city)

        lifecycleScope.launch {
            viewModel.userDetails.collect { userDetails ->
                userDetails?.let {
                    with(binding) {
                        userName.text = it.name
                        userEmail.text = it.email
                        userPhone.text = it.phone
                        userCity.text = it.address?.city
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}