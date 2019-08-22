package com.sudhan.mapprrassgn.contributordetail.model

import com.google.gson.annotations.SerializedName
import com.sudhan.mapprrassgn.searchrepo.model.SearchItemModel

class RepoModel : SearchItemModel() {
    @SerializedName("login")
    lateinit var login:String
}