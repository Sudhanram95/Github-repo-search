package com.sudhan.mapprrassgn.repodetail.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sudhan.mapprrassgn.R
import com.sudhan.mapprrassgn.repodetail.model.ContributorModel
import com.sudhan.mapprrassgn.repodetail.presenter.IRepoDetailPresenter

class ContributorAdapter(var context: Context, var iRepoDetailPresenter: IRepoDetailPresenter, var contributorList:List<ContributorModel>) : RecyclerView.Adapter<ContributorAdapter.Companion.MyViewHolder>() {

    companion object {
        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
        {
            var imgContributor:ImageView
            var txtContributorName:TextView
            init {
                imgContributor = view.findViewById(R.id.img_contributor)
                txtContributorName = view.findViewById(R.id.txt_contributor_name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contributor_item, parent, false)
        val myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return contributorList.size
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        Glide.with(context).load(contributorList.get(position).avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.imgContributor)
        holder.txtContributorName.text = contributorList.get(position).login
        holder.imgContributor.setOnClickListener(View.OnClickListener {
            iRepoDetailPresenter.onContributorItemSelected(contributorList.get(position).login)
        })
    }
}