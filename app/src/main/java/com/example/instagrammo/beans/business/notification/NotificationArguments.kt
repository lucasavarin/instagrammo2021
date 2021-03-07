package com.example.instagrammo.beans.business.notification

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class NotificationArguments(
    var nameProfile : String?,
    var description : String?,
    var iconProfile: Bitmap?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Bitmap::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nameProfile)
        parcel.writeString(description)
        parcel.writeParcelable(iconProfile, flags)
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
