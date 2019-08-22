package com.sudhan.mapprrassgn.contributordetail.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sudhan.mapprrassgn.R
import com.sudhan.mapprrassgn.contributordetail.model.RepoModel

class RepoAdapter(var repoList:List<RepoModel>) : RecyclerView.Adapter<RepoAdapter.Companion.MyViewHolder>() {

    companion object {
        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
        {
            var txtRepoName:TextView
            var txtDesc:TextView
            init {
                txtRepoName = view.findViewById(R.id.txt_repo_name)
                txtDesc = view.findViewById(R.id.txt_description)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contributor_repo_item, parent, false)
        val myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        holder.txtRepoName.text = repoList.get(position).name
        holder.txtDesc.text = repoList.get(position).description
    }
}