package com.github.repos.presentation.user.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.github.repos.BR
import com.github.repos.databinding.ItemUserReposBinding
import com.github.repos.domain.model.UserRepos
import kotlinx.android.synthetic.main.item_user_repos.view.*

/**
 * @author omerakkus
 * @since 07/29/2022
 */

class UserRepositoriesAdapter(var userRepoList: ArrayList<UserRepos>)
    :RecyclerView.Adapter<UserRepositoriesAdapter.UserReposViewHolder>(){

    private lateinit var binding: ItemUserReposBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserReposViewHolder {
       binding = ItemUserReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserReposViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: UserReposViewHolder,
        position: Int
    ) {
        val reposList = userRepoList[position]
        holder.bind(reposList)
        holder.itemView.setOnClickListener {
            holder.itemView.expandableLayout.visibility = View.VISIBLE
        }
    }

    class UserReposViewHolder(
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userRepos: UserRepos) {
            binding.setVariable(BR.userRepos, userRepos)
            binding.executePendingBindings()
        }
    }
    override fun getItemCount(): Int {
        return userRepoList.size
    }
}