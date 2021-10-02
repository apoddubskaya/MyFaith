package com.example.myfaith.presenter

import android.util.Log
import com.example.myfaith.model.ChurchModel
import com.example.myfaith.view.interfaces.IListFragment

class ListFragmentPresenter(val view: IListFragment, private val isDataFavoritesFlag: Boolean) {

    private var churchModel: ChurchModel = ChurchModel(view.getApplicationContext())
    private var lastQuery = ""

    private fun updateList(query: String? = null) {
        if (query != null)
            lastQuery = query
        view.setData(churchModel.getListData(isDataFavoritesFlag, lastQuery))
    }

    fun onStartHandler() {
        updateList()
    }

    fun onQueryTextChangeHandler(query: String?) {
        updateList(query)
    }

    fun itemClickHandler(id: Int) {
        view.openChurchActivity(id)
    }

    fun onFavBtnCheckedChangeHandler(id: Int, isChecked: Boolean) {
        if (isChecked) {
            churchModel.addFavorite(id)
        }
        else {
            churchModel.removeFavorite(id)
            updateList()
        }
    }
}