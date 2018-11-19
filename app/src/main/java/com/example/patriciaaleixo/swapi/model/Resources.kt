/*
 * *************************************************************************
 * Created by:       Patricia Aleixo 11/2018
 * *************************************************************************
 */

package com.example.patriciaaleixo.swapi.model

import java.io.Serializable

data class People(
    val name: String,
    val skin_color: String,
    val gender: String,
    val homeworld: String,
    val species: MutableList<Species>,
    val nvehicles: MutableList<Vehicle>
) : Serializable

data class Species(val name: String) : Serializable {
    override fun toString(): String {
        return name
    }
}

data class Vehicle(val name: String) : Serializable {
    override fun toString(): String {
        return name
    }
}