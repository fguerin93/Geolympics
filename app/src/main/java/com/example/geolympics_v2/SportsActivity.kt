package com.example.geolympics_v2

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_sports.*
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.item_sport.view.*

class SportsActivity : AppCompatActivity() {

    var sports= listOf<Event>(
    )

    lateinit var sportList : MutableList<Sport>
    lateinit var eventList : MutableList<Event>
    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports)

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


        val intent = intent

        val dateChoosedByUser = intent.getStringExtra("date")

        println(dateChoosedByUser)

        ref.addValueEventListener( object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                p0.code
            }

            override fun onDataChange(p0: DataSnapshot) {

                if(p0!!.exists()) {
                    for (h in p0.children) {
                        val sport = h.getValue(Sport::class.java)
                        sportList.add(sport!!)
                        for (i in sport.events) {
                            if (dateChoosedByUser == i.date) {
                                eventList.add(i!!)
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


                    val adapter = SportAdapter(sportList, eventNumberList)

                    val recyclerView = findViewById(R.id.SportsRecyclerView) as RecyclerView
                    recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                    recyclerView.adapter = adapter
                }
            }
        })

    }

    override fun onStart() {
        super.onStart()


    }

}
