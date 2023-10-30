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

            val tilePersonHolder = TilePersonHolder(tpb.nameTv, tpb.totalPriceTv)
            personTileView.tag = tilePersonHolder
        }

        val holder = personTileView.tag as TilePersonHolder
        holder.nameTv.setText(person.name)
        holder.totalPriceTv.setText(person.totalPricePaid.toString())


        tpb?.nameTv?.text = person.name
        tpb?.totalPriceTv?.text = person.totalPricePaid.toString()

        return personTileView
    }

    private class TilePersonHolder(val nameTv: TextView, val totalPriceTv: TextView)
}