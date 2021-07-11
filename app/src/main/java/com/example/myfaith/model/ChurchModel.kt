package com.example.myfaith.model

import android.content.res.Resources
import com.example.myfaith.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class ChurchModel(resources: Resources) {
    companion object {
        var favItems = HashSet<Int>()
    }
    var items: ArrayList<StaticChurch> = ArrayList()
    var itemsHashMap = HashMap<Int, StaticChurch>()
    init {
        val reader = resources.openRawResource(R.raw.churches).bufferedReader()
        val typeToken = object : TypeToken<ArrayList<StaticChurch>>() {}.type
        items = Gson().fromJson<ArrayList<StaticChurch>>(reader, typeToken)
        for (item in items)
            itemsHashMap[item.id] = item
    }

    private fun getFavorites() = favItems

    private fun getFavoritesChurchListElements(): MutableList<ChurchListElement>{
        val mList = mutableListOf<ChurchListElement>()
        val favIds = getFavorites()
        for (id in favIds) {
            val staticItem = itemsHashMap[id]
            if (staticItem != null)
                mList.add(
                        ChurchListElement(
                                name = staticItem.name,
                                adress = staticItem.adress,
                                icon = staticItem.icon,
                                id = staticItem.id,
                                isFavorite = true
                        )
                )
        }
        return mList
    }

    private fun getAllChurchListElements(): MutableList<ChurchListElement>{
        val mList = mutableListOf<ChurchListElement>()
        val favIds = getFavorites()
        for (item in items)
            mList.add(
                    ChurchListElement(
                            name = item.name,
                            adress = item.adress,
                            icon = item.icon,
                            id = item.id,
                            isFavorite = favIds.contains(item.id)
                    )
            )
        return mList
    }


    fun getListData(isDataFavoritesFlag: Boolean, query: String): MutableList<ChurchListElement> {
        val mList = mutableListOf<ChurchListElement>()
        val baseData =
                if (isDataFavoritesFlag) getFavoritesChurchListElements()
                else getAllChurchListElements()
        for (item in baseData)
            if (item.name.toLowerCase().contains(query.toLowerCase()))
                mList.add(item)
        return mList
    }

    fun addFavorite(id: Int) {
        favItems.add(id)
    }

    fun removeFavorite(id: Int) {
        favItems.remove(id)
    }

    fun getChurchPageElement(id: Int): ChurchPageElement? {
        val staticChurch = itemsHashMap[id]
        var churchPageElement: ChurchPageElement? = null
        if (staticChurch != null)
            churchPageElement = ChurchPageElement(
                    name = staticChurch.name,
                    adress = staticChurch.adress,
                    pictures = staticChurch.pictures,
                    info = staticChurch.info,
                    site = staticChurch.site,
                    contactPerson = staticChurch.contactPerson,
                    email = staticChurch.email,
                    phone = staticChurch.phone,
                    id = staticChurch.id,
                    isFavorite = getFavorites().contains(staticChurch.id)
            )
        return churchPageElement
    }

    data class StaticChurch(
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

    data class ChurchPageElement(
            val name: String,
            val adress: String,
            val pictures: List<String>,
            val info: String,
            val site: String,
            val contactPerson: String,
            val email: String,
            val phone: String,
            val id: Int,
            val isFavorite: Boolean
    )

    data class ChurchListElement(
            val name: String,
            val adress: String,
            val icon: String,
            val id: Int,
            val isFavorite: Boolean
    )
}