package com.sudhan.mapprrassgn.searchrepo.view

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sudhan.mapprrassgn.R
import com.sudhan.mapprrassgn.searchrepo.model.SearchItemModel
import com.sudhan.mapprrassgn.searchrepo.presenter.ISearchRepoPresenter

class SearchRepoAdapter(var context: Context, var iSearchRepoPresenter: ISearchRepoPresenter, var repoList:List<SearchItemModel>) : RecyclerView.Adapter<SearchRepoAdapter.Companion.MyViewHolder>() {

    companion object {
        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
        {
            var txtName:TextView
            var txtFullName:TextView
            var txtWatcherCount:TextView
            var txtLanguage:TextView
            var imgAvatar:ImageView
            var card:CardView
            init {
                txtName = view.findViewById(R.id.txt_name)
                txtFullName = view.findViewById(R.id.txt_full_name)
                txtWatcherCount = view.findViewById(R.id.txt_watcher_count)
                txtLanguage = view.findViewById(R.id.txt_language)
                imgAvatar = view.findViewById(R.id.img_avatar)
                card = view.findViewById(R.id.card_item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_repo_item, parent, false)
        val myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        holder.txtName.text = "Name:${repoList.get(position).name}"
        holder.txtFullName.text = "Full name:${repoList.get(position).fullName}"
        holder.txtWatcherCount.text = "Watchers:${repoList.get(position).watchersCount}"
        holder.txtLanguage.text = "Language:${repoList.get(position).language}"
        Glide.with(context).load(repoList.get(position).owner.avatarUrl).into(holder.imgAvatar)
        holder.card.setOnClickListener(View.OnClickListener {
            iSearchRepoPresenter.onRepoItemSelected(repoList.get(position))
        })
    }
}