package com.example.geolympics_v2

import android.content.Context
import android.util.Log
import android.util.Log.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast

class EventChooseAdapter(val mCtx: Context, val layoutResId: Int, val sportChooseList: List<Sport>)
    : ArrayAdapter<Sport>(mCtx, layoutResId, sportChooseList)  {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx);
        val view: View = layoutInflater.inflate(layoutResId, null);


        val textViewName3 = view.findViewById<TextView>(R.id.textViewName3)


        val sportChoose = sportChooseList[position]
        textViewName3.text = sportChoose.name

        return view;
    }
}