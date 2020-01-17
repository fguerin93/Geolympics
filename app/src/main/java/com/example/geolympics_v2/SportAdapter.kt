package com.example.geolympics_v2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class SportAdapter(val sportList: List<Sport>,val eventNumberList: List<String>, val itemClickListener: View.OnClickListener)
    : RecyclerView.Adapter<SportAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById(R.id.sportCardView) as CardView
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
        holder.icon.setImageResource(sport.icon)
        holder.text.text = sport.name
        holder.eventNumber.text = numberComp.toString() + " competitions availables"
        holder.cardView.tag = position
        holder.cardView.setOnClickListener(itemClickListener)
    }

}