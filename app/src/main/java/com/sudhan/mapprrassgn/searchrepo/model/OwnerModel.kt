package com.sudhan.mapprrassgn.searchrepo.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName



class OwnerModel() : Parcelable {
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
    @SerializedName("received_events_url")
    lateinit var receivedEventsUrl: String       
    @SerializedName("type")
    lateinit var type: String

    constructor(parcel: Parcel) : this() {
        login = parcel.readString()
        id = parcel.readInt()
        nodeId = parcel.readString()
        avatarUrl = parcel.readString()
        gravatarId = parcel.readString()
        url = parcel.readString()
        receivedEventsUrl = parcel.readString()
        type = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeInt(id)
        parcel.writeString(nodeId)
        parcel.writeString(avatarUrl)
        parcel.writeString(gravatarId)
        parcel.writeString(url)
        parcel.writeString(receivedEventsUrl)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OwnerModel> {
        override fun createFromParcel(parcel: Parcel): OwnerModel {
            return OwnerModel(parcel)
        }

        override fun newArray(size: Int): Array<OwnerModel?> {
            return arrayOfNulls(size)
        }
    }
}