package com.example.geolympics_v2

import android.os.Bundle
import android.widget.ListView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    class MainActivity : AppCompatActivity() {

        //lateinit var sportListView : ListView
        lateinit var eventListView: ListView
        lateinit var eventChooseListView : ListView
        lateinit var sportList : MutableList<Sport>
        lateinit var eventList : MutableList<Event>
        lateinit var eventChooseList : MutableList<Event>
        lateinit var ref : DatabaseReference

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            sportList = mutableListOf()
            eventList = mutableListOf()
            eventChooseList = mutableListOf()

            ref = FirebaseDatabase.getInstance().getReference("olympic_sports")

            //sportListView = findViewById(R.id.sportListView)
            eventListView = findViewById(R.id.eventListView)
            eventChooseListView = findViewById(R.id.eventAvailableListView)

        }

        override fun onStart() {
            super.onStart()

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
                                eventList.add(i!!)
                            }
                        }

                        val dateChooseByUser = "02/08/2024"
                        val eventAvailableList = arrayListOf<Event>()

                        for ((index, value) in eventList.withIndex()) {
                            if(value.date == dateChooseByUser) {
                                eventAvailableList.add(value)
                            }
                        }

                        //val textViewName = findViewById<TextView>(R.id.textViewName)

                        var sportChooseList = arrayListOf<Sport>()

                        //textViewName.setOnClickListener {
                        //   for ((index, value) in sportList.withIndex()) {
                        //       if(textViewName.text == value.name) {
                        //          sportChooseList.add(value)
                        //       }
                        //   }
                        // }

                        //val adapter = SportAdapter(applicationContext, R.layout.sports, sportList)
                        //sportListView.adapter = adapter

                        val adapter2 = EventAdapter(applicationContext, R.layout.events, eventAvailableList)
                        eventListView.adapter = adapter2

                        val adapter3 = EventChooseAdapter(applicationContext, R.layout.events_available, sportChooseList)
                        eventChooseListView.adapter = adapter3
                    }
                }
            })

        }
    }

}
