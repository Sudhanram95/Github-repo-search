package com.sudhan.mapprrassgn.searchrepo.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

open class SearchItemModel() : Parcelable {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("node_id")
    lateinit var nodeId: String  
    @SerializedName("name")
    lateinit var name: String  
    @SerializedName("full_name")
    lateinit var fullName: String  
    @SerializedName("lateinit")
    var _lateinit: Boolean = false
    @SerializedName("html_url")
    lateinit var htmlUrl: String
    @SerializedName("contributors_url")
    var contributorsUrl: String = ""
    @SerializedName("description")
    var description: String = ""
    @SerializedName("fork")
    var fork: Boolean = false
    @SerializedName("url")
    var url: String = ""
    @SerializedName("created_at")
    var createdAt: String = ""
    @SerializedName("updated_at")
    var updatedAt: String = ""
    @SerializedName("pushed_at")
    var pushedAt: String = ""
    @SerializedName("size")
    var size: String = ""
    @SerializedName("stargazers_count")
    var stargazersCount: String = ""
    @SerializedName("watchers_count")
    lateinit var watchersCount: String
    @SerializedName("language")
    var language: String = ""
    @SerializedName("forks_count")
    var forksCount: String = ""
    @SerializedName("open_issues_count")
    var openIssuesCount: String = ""
    @SerializedName("default_branch")
    var defaultBranch: String = ""
    @SerializedName("score")
    var score: String = ""
    @SerializedName("owner")
    lateinit var owner:OwnerModel

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        nodeId = parcel.readString()
        name = parcel.readString()
        fullName = parcel.readString()
        _lateinit = parcel.readByte() != 0.toByte()
        htmlUrl = parcel.readString()
        contributorsUrl = parcel.readString()
        description = parcel.readString()
        fork = parcel.readByte() != 0.toByte()
        url = parcel.readString()
        createdAt = parcel.readString()
        updatedAt = parcel.readString()
        pushedAt = parcel.readString()
        size = parcel.readString()
        stargazersCount = parcel.readString()
        watchersCount = parcel.readString()
        language = parcel.readString()
        forksCount = parcel.readString()
        openIssuesCount = parcel.readString()
        defaultBranch = parcel.readString()
        score = parcel.readString()
        owner = parcel.readParcelable(OwnerModel::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nodeId)
        parcel.writeString(name)
        parcel.writeString(fullName)
        parcel.writeByte(if (_lateinit) 1 else 0)
        parcel.writeString(htmlUrl)
        parcel.writeString(contributorsUrl)
        parcel.writeString(description)
        parcel.writeByte(if (fork) 1 else 0)
        parcel.writeString(url)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(pushedAt)
        parcel.writeString(size)
        parcel.writeString(stargazersCount)
        parcel.writeString(watchersCount)
        parcel.writeString(language)
        parcel.writeString(forksCount)
        parcel.writeString(openIssuesCount)
        parcel.writeString(defaultBranch)
        parcel.writeString(score)
        parcel.writeParcelable(owner, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchItemModel> {
        override fun createFromParcel(parcel: Parcel): SearchItemModel {
            return SearchItemModel(parcel)
        }

        override fun newArray(size: Int): Array<SearchItemModel?> {
            return arrayOfNulls(size)
        }
    }
}