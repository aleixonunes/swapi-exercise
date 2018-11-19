package com.example.patriciaaleixo.swapi

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.patriciaaleixo.swapi.model.Api
import com.example.patriciaaleixo.swapi.model.People
import com.example.patriciaaleixo.swapi.util.RecyclerItemClickListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {


    var peopleList = mutableListOf<People>()

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
                    intent.putExtra("People", peopleList[position] as Serializable)
                    startActivity(intent)


                }
            })
        )



        val api = Api()
        api.loadPeople()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movie ->
                peopleList.add(movie)
                peopleRecyclerView.adapter?.notifyDataSetChanged()
            }, { e ->
                e.printStackTrace()
            }, {

            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
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
