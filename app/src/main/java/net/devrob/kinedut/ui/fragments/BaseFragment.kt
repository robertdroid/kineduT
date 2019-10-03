package net.devrob.kinedut.ui.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import net.devrob.kinedut.ui.MainActivity
import net.devrob.kinedut.ui.dialogs.ProgressDialog
import net.devrob.kinedut.viewModels.BaseViewModel

abstract class BaseFragment : Fragment() {
    protected lateinit var baseViewModel: BaseViewModel
    private var progressDialog: ProgressDialog? = null
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
        val viewModelHolder = upcastBaseViewModel()
        baseViewModel = viewModelHolder ?: ViewModelProviders.of(this).get(BaseViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        baseViewModel.isLoading.observe(this, Observer { isLoading ->
            isLoading ?: return@Observer

            if (isLoading)
                showLoadingDialog()
            else
                hideLoadingDialog()
        })
    }

    protected abstract fun upcastBaseViewModel(): BaseViewModel?

    fun showLoadingDialog() {
        if(progressDialog == null || !progressDialog!!.isShowing)
            progressDialog = ProgressDialog.show((context as Activity))
    }

    fun hideLoadingDialog() {
        progressDialog?.dismiss()
    }

}