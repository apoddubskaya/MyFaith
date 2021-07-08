package com.example.myfaith.presenter

import com.example.myfaith.view.interfaces.IMainActivity

class MainActivityPresenter(val view: IMainActivity) {

    fun bottomNavigationMapSelectHandler() {
        view.openMap()
    }

    fun bottomNavigationListSelectHandler() {
        view.openList()
    }

    fun bottomNavigationFavoritesSelectHandler() {
        view.openFavorites()
    }

    fun bottomNavigationPrayersSelectHandler() {
        view.openPrayers()
    }

    fun bottomNavigationCalendarSelectHandler() {
        view.openCalendar()
    }
}