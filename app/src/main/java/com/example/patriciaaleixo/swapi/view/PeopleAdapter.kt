/*
 * *************************************************************************
 * Created by:       Patricia Aleixo 11/2018
 * *************************************************************************
 */

package com.example.patriciaaleixo.swapi.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.patriciaaleixo.swapi.R
import com.example.patriciaaleixo.swapi.model.People
import kotlinx.android.synthetic.main.people_cell_view.view.*

class PeopleAdapter(private val peopleList: List<People>,
                    private val context: Context) : Adapter<PeopleAdapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        //Not implemented
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        holder.bindView(peopleList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.people_cell_view, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return peopleList.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(people: People) {
            val name= itemView.nameTextView
            val specie = itemView.specieTextView
            val numberVehicles = itemView.vehiclesTextView

            name.text = people.name
            specie.text = people.species.toString()
            numberVehicles.text = "Nº vehicles: ${people.nvehicles.size}"
        }
    }
}



