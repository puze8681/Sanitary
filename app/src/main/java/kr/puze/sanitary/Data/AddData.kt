package kr.puze.sanitary.Data

import android.os.Parcel
import android.os.Parcelable

data class AddData(
    var title: String? = "",
    var score: Int = 0
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeInt(score)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<AddData> = object : Parcelable.Creator<AddData> {
            override fun createFromParcel(source: Parcel): AddData = AddData(source)
            override fun newArray(size: Int): Array<AddData?> = arrayOfNulls(size)
        }
    }
}