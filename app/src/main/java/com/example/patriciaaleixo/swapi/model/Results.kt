package com.example.patriciaaleixo.swapi.model

import com.google.gson.annotations.SerializedName

data class PeopleResult(val results : List<Person>)


data class Person(val name : String,
                          val skin_color : String,
                          val gender: String,
                          @SerializedName("homeworld")
                          val planetId : String,
                          @SerializedName("species")
                          val speciesList : List<String>,
                          @SerializedName("vehicles")
                          val vehiclesList : List<String>)


data class PlanetResult (val name : String)

data class SpecieResult (val name : String)







