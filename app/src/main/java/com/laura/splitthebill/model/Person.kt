package com.laura.splitthebill.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Person(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @NonNull
    var name: String,
    @NonNull
    var totalPricePaid: Double,
    @NonNull
    var thingsBought: String,
    @NonNull
    var amountToReceive: Double = 0.0,
    @NonNull
    var amountToPay: Double = 0.0
): Parcelable