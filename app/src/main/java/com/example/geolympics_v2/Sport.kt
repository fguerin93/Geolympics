package com.example.geolympics_v2

import android.provider.CalendarContract
import java.util.*

class Sport {

    var id = 0
    var name = ""
    var icon = 0
    var events = emptyList<Event>()

    constructor() {

    }

    constructor(id: Int, name: String, icon: Int, events: List<Event>) {
        this.id = id
        this.name = name
        this.icon = icon
        this.events = events
    }

}