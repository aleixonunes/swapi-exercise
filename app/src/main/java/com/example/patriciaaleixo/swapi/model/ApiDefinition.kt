/*
 * *************************************************************************
 * Created by:       Patricia Aleixo 11/2018
 * *************************************************************************
 */

package com.example.patriciaaleixo.swapi.model

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiDefinition {

    @GET("people/")
    fun listPeople(@Query("page") page: Int): Observable<PeopleResult>

    @GET("planets/{planetId}")
    fun loadPlanet(@Path("planetId") planetId: String?): Observable<PlanetResult>


    @GET("species/{specieId}")
    fun loadSpecies(@Path("specieId") specieId: String?): Observable<SpecieResult>

    @GET("vehicles/{vehicleId}")
    fun loadVehicles(@Path("vehicleId") vehiclesId: String?): Observable<VehicleResult>

}