package com.example.patriciaaleixo.swapi

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.View
import com.example.patriciaaleixo.swapi.model.Api
import com.example.patriciaaleixo.swapi.model.People
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.activity_people_detail.*
import java.io.Serializable

class PeopleDetailActivity : AppCompatActivity() {

    private var peopleName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people_detail)


        var people = intent.getSerializableExtra("People") as? People
        peopleName = people!!.name

        nameTextView.text= "Name: ${people!!.name}"
        genderTextView.text = "Gender: ${people!!.gender}"
        skinColorTextView.text = "Skin Color: ${people!!.skin_color}"



        searchButton.setOnClickListener {     val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, peopleName)
            startActivity(intent) }


        val api = Api()
        api.loadPlanet(people.homeworld)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movie ->
              planetTextView.text = movie.name
            }, { e ->
                e.printStackTrace()
            }, {

            })

    }
}
