package com.example.geolympics_v2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(val eventList: List<Event>, val itemClickListener: View.OnClickListener)
    : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById(R.id.eventCardView) as CardView
        val icon = itemView.findViewById(R.id.imgEvent) as ImageView
        val text = itemView.findViewById(R.id.eventTitle) as TextView
        val timeValues = itemView.findViewById(R.id.timeValues) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewItem = inflater.inflate(R.layout.item_event, parent, false)
        return ViewHolder(viewItem)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onBindViewHolder(holder: EventAdapter.ViewHolder, position: Int) {
        val event = eventList[position]
        val iconList = listOf<Int>(R.mipmap.ic_athletics,R.mipmap.ic_athletics,R.mipmap.ic_fencing,R.mipmap.ic_fencing,R.mipmap.ic_fencing)
        val icon = iconList[position]
        holder.icon.setImageResource(icon)
        holder.text.text = event.name
        holder.timeValues.text = "${event.start_time} - ${event.end_time}"
        holder.cardView.tag = position
        holder.cardView.setOnClickListener(itemClickListener)
    }


}