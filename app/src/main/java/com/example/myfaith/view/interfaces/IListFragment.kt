package com.example.myfaith.view.interfaces

import android.content.Context
import com.example.myfaith.model.ChurchModel

interface IListFragment {
    fun openChurchActivity(position: Int)
    fun getApplicationContext(): Context
    fun setData(data: MutableList<ChurchModel.ChurchListElement>)
    fun removeAtPosition(position: Int)
}