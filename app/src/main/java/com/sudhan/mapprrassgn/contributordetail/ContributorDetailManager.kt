package com.sudhan.mapprrassgn.contributordetail

import com.sudhan.mapprrassgn.contributordetail.model.RepoModel
import com.sudhan.mapprrassgn.contributordetail.view.IContributorDetailView
import com.sudhan.mapprrassgn.network.ApiEndpoints
import com.sudhan.mapprrassgn.network.NetworkUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class ContributorDetailManager(var iContributorDetailView: IContributorDetailView) {
    var apiEndpoints: ApiEndpoints? = NetworkUtil.retrofitHelper()?.create(ApiEndpoints::class.java)

    fun getContributorRepoList(login:String) {
        apiEndpoints?.getContributorRepoList(login)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableObserver<List<RepoModel>>() {
                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    iContributorDetailView.onGetContributorRepoListError("Something went wrong")
                }

                override fun onNext(t: List<RepoModel>) {
                    iContributorDetailView.onGetContributorRepoListSuccess(t)
                }
            })
    }
}