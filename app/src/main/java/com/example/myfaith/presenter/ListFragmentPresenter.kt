package com.example.myfaith.presenter

import com.example.myfaith.model.ChurchModel
import com.example.myfaith.view.interfaces.IListFragment

class ListFragmentPresenter(val view: IListFragment) {
    var churchModel: ChurchModel = ChurchModel(view.getApplicationContext().resources)
    fun onCreateHandler() {
        view.showChurchesList(churchModel.ITEMS)
    }
    fun itemClickHandler(position: Int) {
        view.openChurchActivity(position)
    }
}