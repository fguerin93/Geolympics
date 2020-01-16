package com.example.geolympics_v2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SportAdapter(val sportList: List<Sport>,val eventNumberList: List<String>) : RecyclerView.Adapter<SportAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon = itemView.findViewById(R.id.imgSport) as ImageView
        val text = itemView.findViewById(R.id.sportTitle) as TextView
        val eventNumber = itemView.findViewById(R.id.numberComp) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewItem = inflater.inflate(R.layout.item_sport, parent, false)
        return ViewHolder(viewItem)
    }

    override fun getItemCount(): Int {
        return sportList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sport = sportList[position]
        val event = eventNumberList[position]
        val numberComp = event[0]
        holder.icon.setImageResource(R.mipmap.ic_launcher_round)
        holder.text.text = sport.name
        holder.eventNumber.text = numberComp.toString() + " competitions availables"
    }

}