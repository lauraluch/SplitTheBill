package com.laura.splitthebill.adapter

import android.content.Context
import android.widget.ArrayAdapter
import com.laura.splitthebill.R
import com.laura.splitthebill.model.Person

class PersonAdapter(context: Context, private val peopleList: MutableList<Person>) : ArrayAdapter<Person>(context, R.layout.tile_person, peopleList) {
}