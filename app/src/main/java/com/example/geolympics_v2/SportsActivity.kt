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
import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.activity_sports.*
import kotlinx.android.synthetic.main.activity_sports.buttonSports
import kotlinx.android.synthetic.main.activity_sports.descScreen
import kotlinx.android.synthetic.main.activity_sports.titleScreen
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.item_sport.view.*

class SportsActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var sportList : MutableList<Sport>
    lateinit var sportChoosedList : MutableList<Sport>
    lateinit var eventList : MutableList<Event>
    lateinit var ref : DatabaseReference

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
        setContentView(R.layout.activity_sports)


        //mutable list
        sportList = mutableListOf()
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

        sportList.add(Sport(1, "Swimming", R.mipmap.ic_swimming, swimmingList))
        sportList.add(Sport(2, "Athletics", R.mipmap.ic_athletics, athleticsList))
        sportList.add(Sport(3, "Tennis", R.mipmap.ic_tennis, tennisList))
        sportList.add(Sport(4, "Beach Volley", R.mipmap.ic_volleyball, beachVolleyList))
        sportList.add(Sport(5,"Fencing", R.mipmap.ic_fencing, fencingList))
        sportList.add(Sport(6,"Archery", R.mipmap.ic_archery, archeryList))
        //

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


        //staticstuff
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
        //


        /* FIREBASE NOT WORKING DUE TO CONNECTION
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

         */

    }

    override fun onClick(view: View) {
        val index = view.tag as Int
        val sport = sportList[index]
        Toast.makeText(this, "heyyyyy ${sport}", Toast.LENGTH_SHORT).show()
        sportChoosedList.add(sport)


        // Click on button change route
        buttonSports.setOnClickListener {
            val intent = Intent(this, EventsActivity::class.java)
            intent.putExtra("id", sport.name)
            startActivity(intent)
        }
    }


}
