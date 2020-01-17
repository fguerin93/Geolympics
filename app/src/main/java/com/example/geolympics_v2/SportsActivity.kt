package com.example.geolympics_v2

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.activity_sports.*
<<<<<<< HEAD
=======
import kotlinx.android.synthetic.main.activity_sports.buttonSports
import kotlinx.android.synthetic.main.activity_sports.descScreen
import kotlinx.android.synthetic.main.activity_sports.titleScreen
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.item_sport.view.*
>>>>>>> afd6617ca3158fb79230d0b20fa30c7da8f8ceb3

class SportsActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var sportList : MutableList<Sport>
    lateinit var eventList : MutableList<Event>
    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports)

        Log.d("tag", "hey")

        //mutable list
        sportList = mutableListOf()
        eventList = mutableListOf()

        // ref for db
        ref = FirebaseDatabase.getInstance().getReference("olympic_sports")

        // Fonts
        val typefaceBold = Typeface.createFromAsset(assets, "Eina01-Bold.ttf")
        val typefaceRegular = Typeface.createFromAsset(assets, "Eina01-Regular.ttf")


        // Set the typeface
        titleScreen.typeface = typefaceBold
        descScreen.typeface = typefaceRegular

        // Set main
        titleSports.typeface = typefaceBold
        descSports.typeface = typefaceRegular

        buttonSports.typeface = typefaceBold


        val intentData = intent

        val dateChoosedByUser = intentData.getStringExtra("date")

        // Click on button change route

        buttonSports.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        ref.addValueEventListener( object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                p0.code
            }

            override fun onDataChange(p0: DataSnapshot) {

                if(p0.exists()) {
                    for (h in p0.children) {
                        val sport = h.getValue(Sport::class.java)
                        sportList.add(sport!!)
                        for (i in sport.events) {
                            if (dateChoosedByUser == i.date) {
                                eventList.add(i)
                            }
                        }
                    }

                    val eventNumberList = arrayListOf<String>()
                    for ((key,value) in sportList.withIndex()) {
                        var numberOfEvent = 0
                        for (j in value.events) {
                            if (dateChoosedByUser == j.date) {
                                numberOfEvent = numberOfEvent + 1
                            }
                        }
                        eventNumberList.add(numberOfEvent.toString())
                    }

                    val recyclerView = findViewById<RecyclerView>(R.id.SportsRecyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(applicationContext)

                    val adapter = SportAdapter(sportList, eventNumberList, this@SportsActivity)
                    recyclerView.adapter = adapter


                }
            }
        })

    }

    override fun onClick(view: View) {
        Toast.makeText(this, "heyyyyy", Toast.LENGTH_SHORT).show()
    }


}
