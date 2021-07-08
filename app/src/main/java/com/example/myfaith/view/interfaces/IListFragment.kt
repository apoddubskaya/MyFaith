package com.example.myfaith.view.interfaces

import android.content.Context
import com.example.myfaith.model.ChurchModel

interface IListFragment {
    fun showChurchesList(items: ArrayList<ChurchModel.Church>)
    fun openChurchActivity(position: Int)
    fun getApplicationContext(): Context
}