package com.example.geolympics_v2

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.activity_events.descScreen
import kotlinx.android.synthetic.main.activity_events.titleScreen
import kotlinx.android.synthetic.main.activity_sports.*

class EventsActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var sportChoosedList : MutableList<Sport>
    lateinit var eventList : MutableList<Event>

    //STATIC STUFF TO AVOID FIREBAAAAASE
    lateinit var swimmingList : MutableList<Event>
    lateinit var athleticsList : MutableList<Event>
    lateinit var tennisList : MutableList<Event>
    lateinit var beachVolleyList : MutableList<Event>
    lateinit var fencingList : MutableList<Event>
    lateinit var archeryList : MutableList<Event>
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)


        //mutable list
        sportChoosedList = mutableListOf()
        eventList = mutableListOf()

        //STATIC STUFF TO AVOID FIREBAAAAAAASE
        swimmingList = mutableListOf()
        swimmingList.add(Event("Men's 100m Butterfly Final","18/1/2020", "10:30", "12:30"))
        swimmingList.add(Event("Women's 200m Backstroke Final","18/1/2020", "10:30", "12:30"))
        swimmingList.add(Event("Men's 100m Butterfly Victory Ceremony","19/1/2020", "10:30", "12:30"))
        swimmingList.add(Event("Men's 50m Freestyle Final","20/1/2020", "10:30", "12:30"))
        athleticsList = mutableListOf()
        athleticsList.add(Event("Men's Discus Throw Victory Ceremony","18/1/2020", "19:30", "21:30"))
        athleticsList.add(Event("Men's High Jump Final","18/1/2020", "19:30", "21:30"))
        athleticsList.add(Event("Men's 100m Final","19/1/2020", "9:30", "10:30"))
        athleticsList.add(Event("Men's 100m Victory Ceremony","20/1/2020", "15:30", "17:30"))
        tennisList = mutableListOf()
        tennisList.add(Event("Men's Singles Semifinals","17/1/2020", "19:30", "21:30"))
        tennisList.add(Event("Men's Doubles Gold Medal Match","18/1/2020", "19:30", "21:30"))
        tennisList.add(Event("Men's Singles Bronze Medal Match","19/1/2020", "9:30", "10:30"))
        tennisList.add(Event("Men's Singles Gold Medal Match","20/1/2020", "15:30", "17:30"))
        beachVolleyList = mutableListOf()
        beachVolleyList.add(Event("Women's Gold Medal Match","17/1/2020", "10:30", "12:30"))
        beachVolleyList.add(Event("Men's Gold Medal Match","19/1/2020", "12:30", "14:30"))
        beachVolleyList.add(Event("Men's Victory Ceremony","19/1/2020", "14:30", "15:30"))
        fencingList = mutableListOf()
        fencingList.add(Event("Men's Epée Team Semifinals","17/1/2020", "14:30", "16:30"))
        fencingList.add(Event("Men's Epée Team Bronze Medal Match","18/1/2020", "18:30", "20:30"))
        fencingList.add(Event("Men's Epée Team Gold Medal Match","18/1/2020", "18:30", "20:30"))
        fencingList.add(Event("Men's Sabre Team Victory Ceremony","18/1/2020", "18:30", "20:30"))
        fencingList.add(Event("Women's Sabre Team Gold Medal Match","19/1/2020", "18:30", "20:30"))
        archeryList = mutableListOf()
        archeryList.add(Event("Men's Individual Quarterfinals","18/1/2020", "12:0", "20:00"))
        archeryList.add(Event("Men's Individual Semifinals","18/1/2020", "12:0", "20:00"))
        archeryList.add(Event("Men's Individual Bronze Medal Match","18/1/2020", "12:0", "20:00"))
        archeryList.add(Event("Men's Individual Gold Medal Match","18/1/2020", "12:0", "20:00"))


        //STYLE
        // Fonts
        val typefaceBold = Typeface.createFromAsset(assets, "Eina01-Bold.ttf")
        val typefaceRegular = Typeface.createFromAsset(assets, "Eina01-Regular.ttf")


        // Set the typeface
        titleScreen.typeface = typefaceBold
        descScreen.typeface = typefaceRegular

        // Set main
        titleEvent.typeface = typefaceBold
        descEvent.typeface = typefaceRegular

        buttonEvent.typeface = typefaceBold


        val intentData = intent

        val firstSportChoosed = intentData.getStringExtra("id-first-sport")
        val secondSportChoosed = intentData.getStringExtra("id-second-sport")
        val dateChoosedByUser = intentData.getStringExtra("date")

        sportChoosedList.add(Sport(firstSportChoosed.toInt(), "Athletics", R.mipmap.ic_athletics, athleticsList))
        sportChoosedList.add(Sport(secondSportChoosed.toInt(),"Fencing", R.mipmap.ic_fencing, fencingList))
        for (h in sportChoosedList) {
            val sport = h
            for (i in sport.events) {
                if(dateChoosedByUser == i.date) {
                    eventList.add(i)
                }
            }
        }


        val recyclerView = findViewById<RecyclerView>(R.id.EventsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        val adapter = EventAdapter(eventList, this)
        recyclerView.adapter = adapter
    }

    override fun onClick(v: View?) {

    }

}
