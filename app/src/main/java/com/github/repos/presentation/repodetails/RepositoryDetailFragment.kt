package com.github.repos.presentation.repodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.github.repos.BR
import com.github.repos.R
import com.github.repos.databinding.FragmentRepositoryDetailBinding
import com.github.repos.domain.model.RepoDetails
import com.github.repos.domain.util.ResponseState
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author omerakkus
 * @since 07/29/2022
 */

@AndroidEntryPoint
class RepositoryDetailFragment : Fragment() {
    private var username = ""
    private var repoName = ""
    private lateinit var repoDetails: RepoDetails
    private lateinit var binding: FragmentRepositoryDetailBinding
    private val repoDetailsViewModel: RepositoryDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRepositoryDetailBinding.inflate(LayoutInflater.from(container?.context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = resources.getString(R.string.repository_detail)

        username = arguments?.getString("user_name") as String
        repoName = arguments?.getString("repo_name") as String

        repoDetailsViewModel.getRepositoryDetails(username,repoName)

        repoDetailsViewModel.repositoryDetails.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseState.Loading -> {
                }
                is ResponseState.Success -> {
                    repoDetails = it.data!!
                    binding.setVariable(BR.repoDetails,repoDetails)
                    binding.executePendingBindings()
                    Glide.with(requireContext()).load(repoDetails.avatar).into(binding.ivRepoDetail)
                    binding.ivRepoDetail.setOnClickListener{
                        val fieldBundle = bundleOf("user_name" to  username)
                        findNavController().navigate(R.id.action_repositoryDetailFragment_to_userDetailFragment,fieldBundle)
                    }
                }
                is ResponseState.Error -> {
                    Toast.makeText(requireContext(),it.message?:R.string.connection_error.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

