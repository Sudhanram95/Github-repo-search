package com.sudhan.mapprrassgn.searchrepo.model

import com.google.gson.annotations.SerializedName

class SearchRepoResponse {
    @SerializedName("total_count")
    var totalCount:Int = 0
    @SerializedName("items")
    lateinit var itemsList:ArrayList<SearchItemModel>
}