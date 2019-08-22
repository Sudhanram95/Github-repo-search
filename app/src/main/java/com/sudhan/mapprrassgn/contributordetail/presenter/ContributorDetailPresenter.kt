package com.sudhan.mapprrassgn.contributordetail.presenter

import com.sudhan.mapprrassgn.contributordetail.ContributorDetailManager
import com.sudhan.mapprrassgn.contributordetail.view.IContributorDetailView

class ContributorDetailPresenter(var iContributorDetailView: IContributorDetailView) : IContributorDetailPresenter {

    override fun onGetContributorRepos(login: String) {
        ContributorDetailManager(iContributorDetailView).getContributorRepoList(login)
    }
}