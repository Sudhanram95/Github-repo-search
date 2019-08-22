package com.sudhan.mapprrassgn.contributordetail.view

import com.sudhan.mapprrassgn.contributordetail.model.RepoModel

interface IContributorDetailView {
    fun onGetContributorRepoListSuccess(repos:List<RepoModel>)
    fun onGetContributorRepoListError(errorMessage:String)
}