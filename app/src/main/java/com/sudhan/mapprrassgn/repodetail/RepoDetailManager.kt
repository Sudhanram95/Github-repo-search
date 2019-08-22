package com.sudhan.mapprrassgn.repodetail

import com.sudhan.mapprrassgn.network.ApiEndpoints
import com.sudhan.mapprrassgn.network.NetworkUtil
import com.sudhan.mapprrassgn.repodetail.model.ContributorModel
import com.sudhan.mapprrassgn.repodetail.view.IRepoDetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class RepoDetailManager(var iRepoDetailView: IRepoDetailView) {
    var apiEndpoints: ApiEndpoints? = NetworkUtil.retrofitHelper()?.create(ApiEndpoints::class.java)

    fun getContributorsList(login:String, name:String) {
        apiEndpoints?.getContributorsList(login, name)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableObserver<List<ContributorModel>>() {
                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    iRepoDetailView.onGetContributorListError("Something went wrong")
                }

                override fun onNext(t: List<ContributorModel>) {
                    iRepoDetailView.onGetContributorListSuccess(t)
                }
            })
    }
}