package com.example.myfaith.view.interfaces

import android.content.Context
import com.example.myfaith.model.ChurchModel

interface IListFragment {
    fun openChurchActivity(position: Int)
    fun getApplicationContext(): Context
    fun setData(data: List<ChurchModel.Church>)
}