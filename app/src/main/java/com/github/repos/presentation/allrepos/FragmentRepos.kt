package com.github.repos.presentation.allrepos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.github.repos.R
import com.github.repos.domain.model.AllRepos
import com.github.repos.domain.util.ReposItemDecoration
import com.github.repos.domain.util.ResponseState
import com.github.repos.presentation.allrepos.adapter.AllRepositoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_repos.*

/**
 * @author omerakkus
 * @since 07/29/2022
 */

@AndroidEntryPoint
class FragmentRepos : Fragment() {
    private lateinit var navController: NavController
    private val allRepoListViewModel : AllRepositoriesViewModel by viewModels()
    private var allRepoList:List<AllRepos>?=null
    private lateinit var allReposAdapter: AllRepositoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return  inflater.inflate(R.layout.fragment_repos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = resources.getString(R.string.repositories)

        navController = Navigation.findNavController(view)

        allRepoListViewModel.allRepositories.observe(viewLifecycleOwner){
            when(it){
                is ResponseState.Loading ->{

                }
                is ResponseState.Success ->{
                    allRepoList = it.data
                    recyclerAllRepos.addItemDecoration(
                        ReposItemDecoration(
                            requireContext(),
                            RecyclerView.VERTICAL,
                            32
                        )
                    )
                    allReposAdapter = AllRepositoriesAdapter(object : AllRepositoriesAdapter.ItemClickListener {
                        override fun onClick(username: String,repoName:String) {
                            val fieldBundle = bundleOf("user_name" to  username,"repo_name" to repoName)
                            view.let {
                                navController.navigate(R.id.action_fragmentRepos_to_repositoryDetailFragment,fieldBundle)
                            }
                        }

                    },it.data as ArrayList<AllRepos>)
                    recyclerAllRepos.adapter = allReposAdapter
                }
                is ResponseState.Error -> {
                    Toast.makeText(requireContext(),it.message?: R.string.connection_error.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }
        allRepoListViewModel.getAllRepositories()
    }
}