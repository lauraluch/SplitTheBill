package com.laura.splitthebill.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.laura.splitthebill.databinding.TilePersonBinding
import com.laura.splitthebill.R
import com.laura.splitthebill.model.Person

class PersonAdapter(context: Context, private val peopleList: MutableList<Person>) :
    ArrayAdapter<Person>(context, R.layout.tile_person, peopleList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val person = peopleList[position]
        var tpb: TilePersonBinding? = null

        var personTileView = convertView
        if(personTileView == null){
            tpb = TilePersonBinding.inflate(
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            )
            personTileView = tpb.root

            val tilePersonHolder = TilePersonHolder(tpb.nameTv, tpb.pricePaidTv, tpb.itemsTv)
            personTileView.tag = tilePersonHolder
        }

        val holder = personTileView.tag as TilePersonHolder
        holder.nameTv.setText(person.name)
        holder.pricePaidTv.setText(getPrice(person).toString())
        holder.itemsTv.setText(getItems(person))

//        holder.totalPriceTv.setText(person.totalPricePaid.toString())


        tpb?.nameTv?.text = person.name
        tpb?.pricePaidTv?.text = getPrice(person).toString()
        tpb?.itemsTv?.text = getItems(person)

//        tpb?.totalPriceTv?.text = person.totalPricePaid.toString()

        return personTileView
    }

    fun getPrice(person: Person): Double {
        return person.itemOnePrice + person.itemTwoPrice + person.itemThreePrice
    }

    fun getItems(person: Person) : String {
        var itemOne = checkIfIsEmpty(person.itemOneName, false)
        var itemTwo = checkIfIsEmpty(person.itemTwoName, false)
        var itemThree = checkIfIsEmpty(person.itemThreeName, true)

        return itemOne + itemTwo + itemThree
    }

    fun checkIfIsEmpty(string: String, isLastIndex: Boolean):String {
        if (string.isEmpty() || string.isBlank()) {
            return ""
        }
        if (isLastIndex) return string

        return "$string, "
    }

    private class TilePersonHolder(val nameTv: TextView, val pricePaidTv: TextView, val itemsTv: TextView)
}