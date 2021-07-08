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
    fun onQueryTextChangeHandler(query: String?) {
        val mlist = mutableListOf<ChurchModel.Church>()
        if (query == null || query.isEmpty())
            mlist.addAll(churchModel.ITEMS)
        else
            for (item in churchModel.ITEMS)
                if (item.name.toLowerCase().contains(query.toLowerCase()))
                    mlist.add(item)
        view.changeData(mlist)
    }
}