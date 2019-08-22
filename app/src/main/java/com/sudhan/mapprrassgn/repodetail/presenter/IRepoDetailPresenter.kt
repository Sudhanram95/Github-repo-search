package com.sudhan.mapprrassgn.repodetail.presenter

interface IRepoDetailPresenter {
    fun onGetContributors(login:String?, name:String?)
    fun onContributorItemSelected(login:String)
}