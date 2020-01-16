package com.example.geolympics_v2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class EventAdapter(val mCtx: Context, val layoutResId: Int, val eventList: List<Event>)
    : ArrayAdapter<Event>(mCtx, layoutResId, eventList)  {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx);
        val view: View = layoutInflater.inflate(layoutResId, null);

        val textViewName2 = view.findViewById<TextView>(R.id.textViewName2)

        val dateChooseByUser = "02/08/2024"
        val eventAvailableList = arrayListOf<Event>()

        for ((index, value) in eventList.withIndex()) {
            if(value.date == dateChooseByUser) {
                eventAvailableList.add(value)
            }
        }

        val eventAvailable = eventAvailableList[position]

        textViewName2.text = eventAvailable.name
        return view;
    }
}