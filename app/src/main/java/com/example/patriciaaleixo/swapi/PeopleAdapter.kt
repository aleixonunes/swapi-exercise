package com.example.patriciaaleixo.swapi

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView.Adapter
import com.example.patriciaaleixo.swapi.model.People
import kotlinx.android.synthetic.main.people_cellview.view.*

class PeopleAdapter(private val notes: List<People>,
                    private val context: Context) : Adapter<PeopleAdapter.ViewHolder>() {


    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        val note = notes[position]
        holder.let {
            it.bindView(note)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.people_cellview, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return notes.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(people: People) {
            val name= itemView.nameTextView
            val specie = itemView.specieTextView
            val numberVehicles = itemView.vehiclesTextView
            name.text = people.name
            specie.text = people.species.toString()
            numberVehicles.text = "Nº vehicles: ${people.nvehicles.toString()}"

        }
    }
}



