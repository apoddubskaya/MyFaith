package com.example.myfaith.presenter

import com.example.myfaith.model.ChurchModel
import com.example.myfaith.view.interfaces.IListFragment

class ListFragmentPresenter(val view: IListFragment, private val isDataFavoritesFlag: Boolean) {

    private var churchModel: ChurchModel = ChurchModel(view.getApplicationContext().resources)

    private fun updateList(query: String? = null) {
        val mlist = mutableListOf<ChurchModel.Church>()
        val baseData = churchModel.getListData(isDataFavoritesFlag)
        if (query == null || query.isEmpty())
            mlist.addAll(baseData)
        else
            for (item in baseData)
                if (item.name.toLowerCase().contains(query.toLowerCase()))
                    mlist.add(item)
        view.setData(mlist)
    }

    fun onCreateHandler() {
        updateList()
    }

    fun onQueryTextChangeHandler(query: String?) {
        updateList(query)
    }

    fun itemClickHandler(position: Int) {
        view.openChurchActivity(position)
    }
}