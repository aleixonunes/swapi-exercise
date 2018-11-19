/*
 * *************************************************************************
 * Created by:       Patricia Aleixo 11/2018
 * *************************************************************************
 */

package com.example.patriciaaleixo.swapi.view

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import com.example.patriciaaleixo.swapi.R
import com.example.patriciaaleixo.swapi.model.Api
import com.example.patriciaaleixo.swapi.model.People
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_people_detail.*

class PeopleDetailActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people_detail)

        val people = intent.getSerializableExtra("Person") as? People

        nameTextView.text = getString(R.string.name, people!!.name)
        genderTextView.text = getString(R.string.gender, people.gender)
        skinColorTextView.text = getString(R.string.skin, people.skin_color)

        val adapter = ArrayAdapter(this, R.layout.list_item, people.nvehicles)
        vehiclesListView.adapter = adapter

        val api = Api()
        progressBar.visibility = View.VISIBLE
        api.loadPlanet(people.homeworld)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ home ->
                planetTextView.text = getString(R.string.home, home.name)
            }, { e ->
                e.printStackTrace()
                progressBar.visibility=View.INVISIBLE
            }, {
                progressBar.visibility=View.INVISIBLE
            })

        searchButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, people!!.name)
            startActivity(intent)
        }


    }
}
