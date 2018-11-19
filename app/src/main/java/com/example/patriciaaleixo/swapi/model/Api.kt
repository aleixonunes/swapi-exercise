/*
 * *************************************************************************
 * Created by:       Patricia Aleixo 11/2018
 * *************************************************************************
 */

package com.example.patriciaaleixo.swapi.model

import android.net.Uri
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.functions.Function3
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Api {

    private val service: ApiDefinition

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://swapi.co/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(httpClient.build())
            .build()

        service = retrofit.create<ApiDefinition>(ApiDefinition::class.java)
    }

    fun loadPeople(): Observable<People> {
        return concatPeople(1)
            .flatMap { person ->
                val personObject =
                    People(person.name, person.skin_color, person.gender, person.planetId, ArrayList(), ArrayList())
                Observable.combineLatest(
                    Observable.just(personObject),
                    Observable
                        .fromIterable(person.vehiclesList)
                        .flatMap {
                            service.loadVehicles(Uri.parse(it).lastPathSegment)
                                .map { vehicle ->
                                    Vehicle(vehicle.name)
                                }
                        }
                        .toList()
                        .toObservable(),
                    Observable
                        .fromIterable(person.speciesList)
                        .flatMap {
                            service.loadSpecies(Uri.parse(it).lastPathSegment)
                                .map { specie ->
                                    Species(specie.name)
                                }
                        }
                        .toList()
                        .toObservable(),
                    Function3<People, List<Vehicle>, List<Species>, People> { people, vehicle, specie ->
                        people.nvehicles.addAll(vehicle)
                        people.species.addAll(specie)
                        people
                    })
            }
    }

    private fun concatPeople(page: Int): Observable<Person> {
        return service.listPeople(page)
            .concatMap { peopleList ->
                if (peopleList.next == null)
                    Observable.fromIterable(peopleList.results)
                else Observable.fromIterable(peopleList.results)
                    .concatWith(concatPeople(page + 1))
            }
    }

    fun loadPlanet(planetUrl: String): Observable<PlanetResult> {
        return service.loadPlanet(Uri.parse(planetUrl).lastPathSegment)
    }
}