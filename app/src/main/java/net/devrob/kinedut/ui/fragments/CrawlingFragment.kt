package net.devrob.kinedut.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.tabs.TabLayout
import com.tiper.MaterialSpinner
import kotlinx.android.synthetic.main.fragment_main_crawling.*
import net.devrob.kinedut.R
import net.devrob.kinedut.adapters.SectionsPagerAdapter
import net.devrob.kinedut.commons.SpinnerObject
import net.devrob.kinedut.viewModels.BaseViewModel

class CrawlingFragment : BaseFragment() {
    lateinit var sectionsPagerAdapter: SectionsPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_crawling, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sectionsPagerAdapter = SectionsPagerAdapter(
            mainActivity,
            mainActivity.supportFragmentManager
        )
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

        populateSpinner()
        initEvents()
    }

    private fun initEvents() {
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    mainActivity.currentPage = it.position
                    mainActivity.performSearch()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) { }
            override fun onTabUnselected(tab: TabLayout.Tab?) { }
        })

        spinner_filter.onItemSelectedListener = object : MaterialSpinner.OnItemSelectedListener {
            override fun onItemSelected(
                parent: MaterialSpinner,
                view: View?,
                position: Int,
                id: Long) {
                mainActivity.currentFilter = (spinner_filter.selectedItem as SpinnerObject).id
                mainActivity.performSearch()
            }

            override fun onNothingSelected(parent: MaterialSpinner) {
                mainActivity.currentFilter = -1
            }
        }
    }

    private fun populateSpinner() {
        val filterArray: ArrayList<SpinnerObject> = ArrayList()
        filterArray.add(SpinnerObject(-1, getString(R.string.filter_all_months)))
        filterArray.add(SpinnerObject(1, getString(R.string.filter_0_1_month)))
        for (month in 2..36) filterArray.add(SpinnerObject(month, getString(R.string.filter_n_month, month)))

        val adapter = ArrayAdapter<SpinnerObject>(mainActivity, android.R.layout.simple_spinner_item, filterArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_filter.adapter = adapter
        spinner_filter.selection = 0
    }

    override fun upcastBaseViewModel(): BaseViewModel? {
        return null
    }
}