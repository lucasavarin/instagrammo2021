package com.example.instagrammo.beans.business.notification

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class NotificationArguments(
    var nameProfile : String,
    var description : String,
    var iconProfile: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nameProfile)
        parcel.writeString(description)
        parcel.writeString(iconProfile)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NotificationArguments> {
        override fun createFromParcel(parcel: Parcel): NotificationArguments {
            return NotificationArguments(parcel)
        }

        override fun newArray(size: Int): Array<NotificationArguments?> {
            return arrayOfNulls(size)
        }
    }
}