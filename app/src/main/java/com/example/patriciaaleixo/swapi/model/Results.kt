package com.example.patriciaaleixo.swapi.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PeopleResult(val results : List<Person>,
                        val next : String)

data class FilmResult (val results : List<Film>)

data class Film (val title : String,
                 @SerializedName("episode_id")
                 val episodeId : Int,
                 @SerializedName("characters")
                 val personUrls : List<String>)


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

data class VehiclesResult (val name : String)







