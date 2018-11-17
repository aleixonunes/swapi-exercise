package com.example.patriciaaleixo.swapi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.patriciaaleixo.swapi.model.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var listView : ListView
    lateinit var movieAdapter : ArrayAdapter<String>
    var peopleList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listView = ListView(this)
        setContentView(listView)
        movieAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, peopleList)
        listView.adapter = movieAdapter

        val api = Api()
        api.loadPeople()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                    movie ->
                peopleList.add("${movie.name} -- ${movie.species.toString()}\n ${movie.vehicles.size }")
            }, {
                    e ->
                e.printStackTrace()
            },{
                movieAdapter?.notifyDataSetChanged()
            }).dispose()
    }
}