package com.sudhan.mapprrassgn.repodetail.presenter

import android.content.Context
import android.content.Intent
import com.sudhan.mapprrassgn.contributordetail.view.ContributorDetailActivity
import com.sudhan.mapprrassgn.repodetail.RepoDetailManager
import com.sudhan.mapprrassgn.repodetail.view.IRepoDetailView

class RepoDetailPresenter(var context: Context, var iRepoDetailView: IRepoDetailView) : IRepoDetailPresenter {

    override fun onGetContributors(login: String?, name: String?) {
        RepoDetailManager(iRepoDetailView).getContributorsList(login!!, name!!)
    }

    override fun onContributorItemSelected(login: String) {
        val intent = Intent(context, ContributorDetailActivity::class.java)
        intent.putExtra("login", login)
        context.startActivity(intent)
    }
}