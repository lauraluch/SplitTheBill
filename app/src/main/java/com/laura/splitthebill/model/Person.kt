package com.laura.splitthebill.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    var id: Int?,
    var name: String,
    var totalPricePaid: Double,
    var thingsBought: String
): Parcelable