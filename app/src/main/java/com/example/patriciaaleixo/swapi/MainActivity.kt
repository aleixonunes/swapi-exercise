package com.example.patriciaaleixo.swapi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.patriciaaleixo.swapi.model.Api
import com.example.patriciaaleixo.swapi.model.People
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.AdapterView.OnItemClickListener
import android.support.v7.widget.RecyclerView



class MainActivity : AppCompatActivity() {

    lateinit var listView : ListView
    lateinit var movieAdapter : ArrayAdapter<String>
    var peopleList = mutableListOf<People>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        peopleRecyclerView.layoutManager = LinearLayoutManager(this)
        peopleRecyclerView.layoutManager=GridLayoutManager(this,2)
        peopleRecyclerView.adapter = PeopleAdapter(peopleList, this)


        val api = Api()
        api.loadPeople()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                    movie ->
                peopleList.add(movie)
                peopleRecyclerView.adapter?.notifyDataSetChanged()
            }, {
                    e ->
                e.printStackTrace()
            },{

            })


    }
}