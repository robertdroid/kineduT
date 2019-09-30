package net.devrob.kinedut.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.devrob.kinedut.adapters.ActivitiesAdapter
import net.devrob.kinedut.adapters.ArticlesAdapter
import net.devrob.kinedut.api.RetrofitHelper
import net.devrob.kinedut.models.Activity
import net.devrob.kinedut.models.Article

class PageViewModel : BaseViewModel() {
    private var context: Context? = null
    private val activities = MutableLiveData<ArrayList<Activity>>()
    private val articles = MutableLiveData<ArrayList<Article>>()

    val adapterActivity: LiveData<ActivitiesAdapter> = Transformations.map(activities) {
        val adapter = ActivitiesAdapter(context!!, activities.value!!)
        adapter
    }

    val adapterArticle: LiveData<ArticlesAdapter> = Transformations.map(articles) {
        val adapter = ArticlesAdapter(context!!, articles.value!!)
        adapter
    }


    fun setIndex(sectionNum: Int, filterMonth: Int, context: Context) {
        this.context = context
        if (sectionNum == 1)
            getActivities()
        else {
            getArticles()
        }
    }

    private fun getActivities() {
        GlobalScope.launch {
            isLoading.postValue(true)
            val result = RetrofitHelper.getActivities(5)
            if (result.isError) {
                //TODO perform error actions
            } else {
                if (result.statusCode == 200 && result.objeto != null) {
                    activities.postValue(result.objeto?.data?.activities)
                } else {
                    //TODO: perform no response actions
                }
            }
            isLoading.postValue(false)
        }
    }

    private fun getArticles() {
        GlobalScope.launch {
            isLoading.postValue(true)
            val result = RetrofitHelper.getArticles(5)
            if (result.isError) {
                // TODO perform error actions
            } else {
                if (result.statusCode == 200 && result.objeto != null) {
                    articles.postValue(result.objeto?.data?.articles)
                } else {
                    //TODO perform no response actions
                }
            }
            isLoading.postValue(false)
        }
    }

}