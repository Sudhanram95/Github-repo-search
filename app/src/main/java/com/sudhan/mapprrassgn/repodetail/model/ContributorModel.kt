package com.sudhan.mapprrassgn.repodetail.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class ContributorModel {
    @SerializedName("login")
    lateinit var login: String  
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("node_id")
    lateinit var nodeId: String  
    @SerializedName("avatar_url")
    lateinit var avatarUrl: String  
    @SerializedName("gravatar_id")
    lateinit var gravatarId: String  
    @SerializedName("url")
    lateinit var url: String  
    @SerializedName("html_url")
    lateinit var htmlUrl: String  
    @SerializedName("followers_url")
    lateinit var followersUrl: String  
    @SerializedName("following_url")
    lateinit var followingUrl: String  
    @SerializedName("gists_url")
    lateinit var gistsUrl: String  
    @SerializedName("starred_url")
    lateinit var starredUrl: String  
    @SerializedName("subscriptions_url")
    lateinit var subscriptionsUrl: String  
    @SerializedName("organizations_url")
    lateinit var organizationsUrl: String  
    @SerializedName("repos_url")
    lateinit var reposUrl: String  
    @SerializedName("events_url")
    lateinit var eventsUrl: String  
    @SerializedName("received_events_url")
    lateinit var receivedEventsUrl: String  
    @SerializedName("type")
    lateinit var type: String  
    @SerializedName("site_admin")
    var siteAdmin: Boolean = false
    @SerializedName("contributions")
    var contributions: Int = 0
}