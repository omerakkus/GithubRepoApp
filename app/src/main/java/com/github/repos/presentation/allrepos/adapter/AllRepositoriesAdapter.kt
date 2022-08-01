package com.github.repos.presentation.allrepos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.repos.BR
import com.github.repos.databinding.ItemRepoBinding
import com.github.repos.domain.model.AllRepos
import kotlinx.android.synthetic.main.item_repo.view.*

/**
 * @author omerakkus
 * @since 07/29/2022
 */

class AllRepositoriesAdapter(private val listener: ItemClickListener, private val allRepoList:List<AllRepos>):
RecyclerView.Adapter<AllRepositoriesAdapter.AllReposViewHolder>() {

    private lateinit var binding: ItemRepoBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllRepositoriesAdapter.AllReposViewHolder {
        binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllRepositoriesAdapter.AllReposViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AllRepositoriesAdapter.AllReposViewHolder,
        position: Int
    ) {
        val reposList = allRepoList[position]
        holder.bind(reposList)
        holder.itemView.setOnClickListener {
            listener.onClick(reposList.ownerName,reposList.repoName)
        }
    }

    class AllReposViewHolder(
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(allRepos: AllRepos) {
            binding.setVariable(BR.allRepos, allRepos)
            Glide.with(itemView.context).load(allRepos.avatarUrl).into(itemView.ivAvatar)
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return allRepoList.size
    }

    interface ItemClickListener {
        fun onClick(username: String, repoName:String)
    }
}