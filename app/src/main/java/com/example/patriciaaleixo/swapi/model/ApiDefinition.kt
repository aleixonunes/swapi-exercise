package com.example.patriciaaleixo.swapi.model

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiDefinition {

    @GET("people/")
    fun listPeople(@Query("page") page: Int): Observable<PeopleResult>

    @GET("planets/{planetId}")
    fun loadPlanet(@Path("planetId") planetId: String): Observable<PlanetResult>

    @GET("species/{specieId}")
    fun loadSpecies(@Path("specieId") specieId: String): Observable<SpecieResult>

    @GET("vehicles/{vehicleId}")
    fun loadVehicles(@Path("vehiclesId") vehiclesId: String): Observable<VehiclesResult>

    @GET("films")
    fun listMovies(): Observable<FilmResult>

    @GET("people/{personId}")
    fun loadPerson(@Path("personId") personId: String): Observable<Person>

}