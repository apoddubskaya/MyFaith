package com.example.myfaith.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myfaith.R
import com.example.myfaith.databinding.ActivityMainBinding
import com.example.myfaith.presenter.MainActivityPresenter
import com.example.myfaith.view.interfaces.IMainActivity
import com.example.myfaith.view.list.ListFragment


class MainActivity : AppCompatActivity(), IMainActivity {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainActivityPresenter(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_map -> presenter.bottomNavigationMapSelectHandler()
                R.id.bottom_nav_list -> presenter.bottomNavigationListSelectHandler()
                R.id.bottom_nav_favorites -> presenter.bottomNavigationFavoritesSelectHandler()
                R.id.bottom_nav_prayers -> presenter.bottomNavigationPrayersSelectHandler()
                R.id.bottom_nav_calendar -> presenter.bottomNavigationCalendarSelectHandler()
            }
            true
            }
    }

    private fun replaceFragment(selectedFragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, selectedFragment)
                .commit()
    }

    override fun onBackPressed() {
        var handled: Boolean = false
        for (fragment in supportFragmentManager.fragments)
            if (fragment is ListFragment)
                handled = handled || fragment.onBackPressed()
        if (!handled)
            super.onBackPressed()
    }

    override fun openMap() {
        replaceFragment(MapFragment())
    }

    override fun openList() {
        replaceFragment(ListFragment())
    }

    override fun openFavorites() {
        replaceFragment(FavoritesFragment())
    }

    override fun openPrayers() {
        replaceFragment(PrayersFragment())
    }

    override fun openCalendar() {
        replaceFragment(CalendarFragment())
    }
}
