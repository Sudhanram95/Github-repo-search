package com.sudhan.mapprrassgn.repodetail.view

import com.sudhan.mapprrassgn.repodetail.model.ContributorModel

interface IRepoDetailView {
    fun onGetContributorListSuccess(contributors:List<ContributorModel>)
    fun onGetContributorListError(errorMessage:String)
}