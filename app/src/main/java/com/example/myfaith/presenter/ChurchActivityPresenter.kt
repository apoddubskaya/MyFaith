package com.example.myfaith.presenter


import com.example.myfaith.model.ChurchModel
import com.example.myfaith.view.interfaces.IChurchActivity

class ChurchActivityPresenter(val view: IChurchActivity) {
    private val churchModel = ChurchModel(view.getApplicationContext().resources)
    fun onCreateHandler(position: Int) {
        view.setData(churchModel.ITEMS[position])
    }
}