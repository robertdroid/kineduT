package net.devrob.kinedut.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_crawling.*
import net.devrob.kinedut.R
import net.devrob.kinedut.ui.MainActivity
import net.devrob.kinedut.viewModels.BaseViewModel
import net.devrob.kinedut.viewModels.PageViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : BaseFragment(), MainActivity.SearchListener {
    private lateinit var pageViewModel: PageViewModel
    private var currentPage: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_crawling, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.setSearchListener(this)
        currentPage = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1
        pageViewModel = baseViewModel as PageViewModel
        pageViewModel.apply {
            setIndex(currentPage, mainActivity, mainActivity.currentFilter)
        }

        pageViewModel.adapterActivity.observe(this, Observer {
            rvData.adapter = it
            rvData.layoutManager = LinearLayoutManager(activity)
            it.notifyDataSetChanged()
        })

        pageViewModel.adapterArticle.observe(this, Observer {
            rvData.adapter = it
            it.setOnSelectedListener { article ->
                val bundle = bundleOf("article" to article)
                findNavController().navigate(R.id.action_crawlingFragment_to_articleDetailFragment, bundle)
            }
            rvData.layoutManager = LinearLayoutManager(activity)
            it.notifyDataSetChanged()
        })
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun upcastBaseViewModel(): BaseViewModel? {
        return ViewModelProviders.of(this).get(PageViewModel::class.java)
    }

    override fun performSearch() {
        if ((mainActivity.currentPage + 1) == 1) {
            pageViewModel.getActivities(mainActivity.currentFilter)
        } else {
            pageViewModel.getArticles()
        }
    }
}