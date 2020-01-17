package com.example.geolympics_v2

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_events.*

class EventsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        val intentData = intent

        val idSportChoosed = intentData.getStringExtra("id")

        sportChoosed.text = idSportChoosed
    }

}
