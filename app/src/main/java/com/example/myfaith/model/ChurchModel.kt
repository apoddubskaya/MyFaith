package com.example.myfaith.model

import android.content.res.Resources
import com.example.myfaith.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class ChurchModel(resources: Resources) {
    var ITEMS: ArrayList<Church> = ArrayList()
    init {
        val reader = resources.openRawResource(R.raw.churches).bufferedReader()
        val typeToken = object : TypeToken<ArrayList<Church>>() {}.type
        ITEMS = Gson().fromJson<ArrayList<Church>>(reader, typeToken)
    }

    fun getListData(isDataFavoritesFlag: Boolean): List<Church> {
        return if (isDataFavoritesFlag) listOf(ITEMS[0], ITEMS[1], ITEMS[2])
        else ITEMS
    }

    data class Church(
            val name: String,
            val adress: String,
            val icon: String,
            val pictures: List<String>,
            val info: String,
            val site: String,
            val contactPerson: String,
            val email: String,
            val phone: String,
            val id: Int
    )
}