package com.laura.splitthebill.model

data class Person (
    var id: Int,
    var name: String,
    var totalPricePaid: Double,
    var thingsBought: List<String>
)