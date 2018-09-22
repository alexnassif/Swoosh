package com.alexnassif.mobile.tennisbro.Model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by raymond on 11/24/17.
 */
class Player :Parcelable{

    lateinit var league: String
    lateinit var skill: String
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    lateinit var name: String

    constructor(){

    }

    constructor(league: String, skill: String, latitude: Double, longitude: Double,
                name: String){
        this.league = league
        this.skill = skill
        this.latitude = latitude
        this.longitude = longitude
        this.name = name
    }

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(league)
        parcel.writeString(skill)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }

}