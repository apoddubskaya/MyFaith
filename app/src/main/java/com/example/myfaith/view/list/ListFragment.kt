package com.example.myfaith.view.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.myfaith.R
import com.example.myfaith.databinding.FragmentListBinding
import com.example.myfaith.model.ChurchModel
import com.example.myfaith.presenter.ListFragmentPresenter
import com.example.myfaith.view.churchpage.ChurchActivity
import com.example.myfaith.view.interfaces.IListFragment

/**
 * A fragment representing a list of Items.
 */
class ListFragment : Fragment(), IListFragment {
    private lateinit var binding: FragmentListBinding
    private lateinit var presenter: ListFragmentPresenter
    private lateinit var searchMenuItem: MenuItem
    private lateinit var adapter: ListRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val isDataFavorites = arguments?.getBoolean("isDataFavorites", false) ?: false
        presenter = ListFragmentPresenter(this, isDataFavorites)
        binding = FragmentListBinding.inflate(inflater, container, false)

        if (isDataFavorites)
            binding.listFragmentToolbarText.text = resources.getString(
                    R.string.toolbar_favorites_text
            )
        setAdapter()
        searchMenuItem  = binding.listFragmentToolbar.menu.findItem(R.id.list_fragment_search_item)
        val view = binding.root
        presenter.onCreateHandler()
        val searchView = searchMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(
                object: SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        presenter.onQueryTextChangeHandler(newText)
                        return false
                    }
                }
        )
        binding.list.setOnTouchListener { _, _ ->
            val searchView = searchMenuItem.actionView as SearchView
            searchView.clearFocus()
            false
        }
        return view
    }

    fun onBackPressed(): Boolean {
        return if (searchMenuItem.isActionViewExpanded) {
            searchMenuItem.collapseActionView()
        } else false
    }

    private fun setAdapter() {
        adapter = ListRecyclerViewAdapter(getApplicationContext(), listOf()) {
            presenter.itemClickHandler(it)
        }
        binding.list.adapter = adapter
    }

    override fun openChurchActivity(position: Int) {
        val intent = Intent(requireContext(), ChurchActivity::class.java).apply {
            putExtra(ChurchActivity.CHURCH_POSITION_EXTRA_NAME, position)
        }
        startActivity(intent)
    }

    override fun getApplicationContext(): Context = requireActivity().applicationContext

    override fun setData(data: List<ChurchModel.Church>) {
        if (data.isEmpty()) {
            binding.textviewResultsNotFound.visibility = View.VISIBLE
            binding.list.visibility = View.GONE
        }
        else {
            adapter.updateValues(data)
            binding.list.visibility = View.VISIBLE
            binding.textviewResultsNotFound.visibility = View.GONE
        }
    }
}