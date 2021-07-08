package com.example.myfaith.view.churchpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfaith.R
import com.example.myfaith.databinding.ActivityChurchBinding
import com.example.myfaith.model.ChurchModel
import com.example.myfaith.presenter.ChurchActivityPresenter
import com.example.myfaith.view.interfaces.IChurchActivity

class ChurchActivity : AppCompatActivity(), IChurchActivity {
    private lateinit var binding: ActivityChurchBinding
    private lateinit var presenter: ChurchActivityPresenter
    companion object {
        const val CHURCH_POSITION_EXTRA_NAME = "church_position"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChurchBinding.inflate(layoutInflater)
        val view = binding.root
        presenter = ChurchActivityPresenter(this)
        presenter.onCreateHandler(intent.getIntExtra(CHURCH_POSITION_EXTRA_NAME, -1))
        setContentView(view)
    }

    override fun setData(church: ChurchModel.Church) {
        binding.churchActivityViewpager.adapter = ChurchImagesRecyclerViewAdapter(
                church.pictures
        )
        binding.churchActivityDotsIndicator.setViewPager2(binding.churchActivityViewpager)

        binding.churchActivityName.text = church.name
        binding.churchActivityInfo.text = church.info

        binding.churchActivityAdress.text = prepareKeyValueString(
                getString(R.string.bottom_nav_calendar_text),
                church.adress
        )
        binding.churchActivitySite.text = prepareKeyValueString(
                getString(R.string.bottom_nav_calendar_text),
                church.site
        )
        binding.churchActivityContactPerson.text = prepareKeyValueString(
                getString(R.string.bottom_nav_calendar_text),
                church.contactPerson
        )
        binding.churchActivityEmail.text = prepareKeyValueString(
                getString(R.string.bottom_nav_calendar_text),
                church.email
        )
        binding.churchActivityPhone.text = prepareKeyValueString(
                getString(R.string.bottom_nav_calendar_text),
                church.phone
        )

    }

    private fun prepareKeyValueString(key: String, value: String) = "$key:\n$value"
}