package com.example.myfaith.presenter


import com.example.myfaith.model.ChurchModel
import com.example.myfaith.view.interfaces.IChurchActivity

class ChurchActivityPresenter(val view: IChurchActivity) {
    private val churchModel = ChurchModel(view.getApplicationContext())
    fun onCreateHandler(id: Int) {
        val churchPageElement = churchModel.getChurchPageElement(id)
        if (churchPageElement != null)
            view.setData(churchPageElement)
        else view.errorHappend()
    }

    fun onFavBtnCheckedChangeHandler(id: Int, isChecked: Boolean) {
        if (isChecked) {
            churchModel.addFavorite(id)
        }
        else {
            churchModel.removeFavorite(id)
        }
    }
}