package com.sudhan.mapprrassgn.searchrepo

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.sudhan.mapprrassgn.network.ApiEndpoints
import com.sudhan.mapprrassgn.network.NetworkUtil
import com.sudhan.mapprrassgn.searchrepo.model.SearchRepoResponse
import com.sudhan.mapprrassgn.searchrepo.view.ISearchRepoView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SearchRepoManager(var iSearchRepoView: ISearchRepoView) {
    var apiEndpoints: ApiEndpoints? = NetworkUtil.retrofitHelper()?.create(ApiEndpoints::class.java)

    fun getRepoList(searchKey:String) {
        apiEndpoints?.getRepoList(searchKey, "stars")
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<SearchRepoResponse>() {
                override fun onSuccess(searchRepoResponse: SearchRepoResponse) {
                    val only10Repos = searchRepoResponse.itemsList.take(10)
                    Log.e("SearchRepoManager", "Success:::"+only10Repos.size)
                    Log.e("SearchRepoManager", "Success:::"+searchKey)
                    iSearchRepoView.onSearchRepoApiSuccess(only10Repos)
                }

                override fun onError(e: Throwable) {
                    Log.e("SearchRepoManager", "Error:::"+e.toString())
                    Log.e("SearchRepoManager", "Error:::"+searchKey)
                    if(e is HttpException) {
                        val errorCode = e.code()
                        Log.e("SearchRepoManager", "Error:::"+errorCode)
                        if(errorCode == 403) {
                            iSearchRepoView.onSearchRepoApiError("403 Forbidden")
                            return
                        }
                        iSearchRepoView.onSearchRepoApiError("Something went wrong")
                    }
                }
            })
    }
}