package com.example.patriciaaleixo.swapi.model

import com.google.gson.annotations.SerializedName


data class People(val name : String,
                  val skin_color : String,
                  val gender: String,
                  val homeworld : String,
                  val species :MutableList<Species>,
                  val vehicles : MutableList<Vehicles>)

data class Species(val name : String){
    override fun toString(): String {
        return "${name}"
    }
}

data class Vehicles(val name: String, val model: String){

    override fun toString(): String {
        return "${name} / ${model}"
    }
}

data class Planet (val name:String)


data class Movie (val title : String,
                  val episodeId : Int,
                  val characters : MutableList<Character>)

data class Character(val name : String,
                     val gender : String){

    override fun toString(): String {
        return "${name} / ${gender}"
    }
}