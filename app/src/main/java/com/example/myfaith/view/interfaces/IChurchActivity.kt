package com.example.myfaith.view.interfaces

import android.content.Context
import com.example.myfaith.model.ChurchModel

interface IChurchActivity {
    fun setData(church: ChurchModel.Church)
    fun getApplicationContext(): Context
}