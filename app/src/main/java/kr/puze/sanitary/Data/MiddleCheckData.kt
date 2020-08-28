package kr.puze.sanitary.Data

import android.os.Parcel
import android.os.Parcelable

data class MiddleCheckData(
    var index: String? = "",
    var text: String?= "",
    var totalScore: Int = -1,
    var noApplicable: Boolean = false,
    var endList: ArrayList<EndCheckData>? = ArrayList<EndCheckData>()
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readInt(),
        1 == source.readInt(),
        source.createTypedArrayList(EndCheckData.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(index)
        writeString(text)
        writeInt(totalScore)
        writeInt((if (noApplicable) 1 else 0))
        writeTypedList(endList)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MiddleCheckData> =
            object : Parcelable.Creator<MiddleCheckData> {
                override fun createFromParcel(source: Parcel): MiddleCheckData =
                    MiddleCheckData(source)

                override fun newArray(size: Int): Array<MiddleCheckData?> = arrayOfNulls(size)
            }
    }
}