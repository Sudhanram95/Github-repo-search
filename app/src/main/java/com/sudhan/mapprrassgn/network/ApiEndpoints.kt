package com.sudhan.mapprrassgn.network

import com.sudhan.mapprrassgn.contributordetail.model.RepoModel
import com.sudhan.mapprrassgn.repodetail.model.ContributorModel
import com.sudhan.mapprrassgn.searchrepo.model.SearchRepoResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoints {
    @GET("search/repositories")
    fun getRepoList(@Query("q") searchKey:String, @Query("sort") sortOrder:String): Single<SearchRepoResponse>

    @GET("repos/{login}/{name}/contributors")
    fun getContributorsList(@Path("login") repoName:String, @Path("name") name:String):Observable<List<ContributorModel>>

    @GET("users/{login}/repos")
    fun getContributorRepoList(@Path("login") login:String):Observable<List<RepoModel>>
}