/*
 * *************************************************************************
 * Created by:       Patricia Aleixo 11/2018
 * *************************************************************************
 */

package com.example.patriciaaleixo.swapi.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.patriciaaleixo.swapi.R
import com.example.patriciaaleixo.swapi.model.Api
import com.example.patriciaaleixo.swapi.model.People
import com.example.patriciaaleixo.swapi.util.RecyclerItemClickListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    var peopleList = mutableListOf<People>()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.app_bar))

        peopleRecyclerView.layoutManager = LinearLayoutManager(this)
        peopleRecyclerView.layoutManager = GridLayoutManager(this, 2)
        peopleRecyclerView.adapter = PeopleAdapter(peopleList, this)
        peopleRecyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {

                    val intent = Intent(applicationContext, PeopleDetailActivity::class.java)
                    intent.putExtra("Person", peopleList[position] as Serializable)
                    startActivity(intent)
                }
            })
        )

        val api = Api()
        progressBar.visibility = View.VISIBLE
        api.loadPeople()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ people ->
                peopleList.add(people)
                peopleRecyclerView.adapter?.notifyDataSetChanged()
            }, { e ->
                e.printStackTrace()
                progressBar.visibility = View.INVISIBLE
            }, {
                progressBar.visibility = View.INVISIBLE
            })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.navigation, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(applicationContext, ProfileActivity::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}
