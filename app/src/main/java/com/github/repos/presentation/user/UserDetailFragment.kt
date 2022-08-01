package com.github.repos.presentation.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.repos.BR
import com.github.repos.R
import com.github.repos.databinding.FragmentUserDetailBinding
import com.github.repos.domain.model.UserInfo
import com.github.repos.domain.model.UserRepos
import com.github.repos.domain.util.ReposItemDecoration
import com.github.repos.domain.util.ResponseState
import com.github.repos.presentation.user.adapter.UserRepositoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_detail.*

/**
 * @author omerakkus
 * @since 07/29/2022
 */

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    private var username=""
    private lateinit var binding: FragmentUserDetailBinding
    private val userRepositoriesViewModel: UserRepositoriesViewModel by viewModels()

    private lateinit var userRepoList: List<UserRepos>
    private lateinit var userProfileInfo: UserInfo
    private lateinit var userReposAdapter: UserRepositoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailBinding.inflate(LayoutInflater.from(container?.context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = resources.getString(R.string.user_detail)

        username = arguments?.getString("user_name") as String

        userRepositoriesViewModel.userProfileInfo.observe(viewLifecycleOwner){
            when(it){
                is ResponseState.Loading ->{

                }
                is ResponseState.Success ->{
                    userProfileInfo = it.data!!
                    binding.setVariable(BR.userProfileInfo,userProfileInfo)
                    binding.executePendingBindings()
                    Glide.with(requireContext()).load(userProfileInfo.avatarUrl).into(ivUserProfile)
                }
                is ResponseState.Error -> {
                    Toast.makeText(requireContext(),it.message?: R.string.connection_error.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

        userRepositoriesViewModel.userRepositories.observe(viewLifecycleOwner){
            when(it){
                is ResponseState.Loading ->{
                }
                is ResponseState.Success ->{
                    rvUserRepos.addItemDecoration(
                        ReposItemDecoration(
                            requireContext(),
                            RecyclerView.VERTICAL,
                            32
                        )
                    )
                    userRepoList = it.data!!
                    userReposAdapter = UserRepositoriesAdapter(it.data as ArrayList<UserRepos>)
                    rvUserRepos.adapter = userReposAdapter
                }
                is ResponseState.Error -> {
                    Toast.makeText(requireContext(),it.message?: R.string.connection_error.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

        userRepositoriesViewModel.getUserProfileInfo(username)
        userRepositoriesViewModel.getUserRepositories(username)
    }
}