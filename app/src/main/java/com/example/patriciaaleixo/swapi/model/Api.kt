package com.example.patriciaaleixo.swapi.model

import android.net.Uri
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.function.BiFunction

class Api {
    val service: ApiDefinition

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://swapi.co/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()

        service = retrofit.create<ApiDefinition>(ApiDefinition::class.java)
    }


    fun loadPeople(): Observable<People> {
        return service.listPeople()
            .flatMap { peopleResults -> Observable.fromIterable(peopleResults.results) }
            .flatMap { person ->
                val peopleObj = People(person.name,person.skin_color, person.gender, person.planetId, ArrayList(), ArrayList())
                Observable.zip(
                    Observable
                        .just(peopleObj),
                    Observable
                        .fromIterable(person.speciesList)
                        .flatMap { specieUrl ->
                            service.loadSpecies(Uri.parse(specieUrl).lastPathSegment)
                                .take(1)
                                .map { specie ->
                                    Species(specie.name)
                                }
                        }
                        .toList()
                        .toObservable(),
                    io.reactivex.functions.BiFunction<People, List<Species>, People> { people, species ->
                        people.species.addAll(species)
                        people
                    })
            }
    }
}