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
import net.devrob.kinedut.commons.DataSession
import net.devrob.kinedut.models.Activity
import net.devrob.kinedut.models.Article

class PageViewModel : BaseViewModel() {
    private var context: Context? = null
    private val activities = MutableLiveData<ArrayList<Activity>>()
    private val articles = MutableLiveData<ArrayList<Article>>()

    val adapterActivity: LiveData<ActivitiesAdapter> = Transformations.map(activities) {
        val adapter = ActivitiesAdapter(context!!, activities.value!!)
        adapter.updateValues(activities.value!!)
        adapter
    }

    val adapterArticle: LiveData<ArticlesAdapter> = Transformations.map(articles) {
        val adapter = ArticlesAdapter(context!!, articles.value!!)
        adapter
    }


    fun setIndex(sectionNum: Int, context: Context, filterMonth: Int = -1) {
        this.context = context
        if (sectionNum == 1)
            getActivities(filterMonth)
        else {
            getArticles()
        }
    }

    fun getActivities(filterMonth: Int = -1) {
        if (DataSession.activities.isNotEmpty()) {
            if (filterMonth <= 0)
                activities.postValue(DataSession.activities)
            else {
                activities.postValue(
                    ArrayList(
                        DataSession.activities.filter {
                            it.age == filterMonth
                        })
                )
            }
        } else {
            GlobalScope.launch {
                isLoading.postValue(true)
                val result = RetrofitHelper.getActivities(5)
                if (result.isError) {
                    //TODO perform error actions
                } else {
                    if (result.statusCode == 200 && result.objeto != null) {
                        DataSession.activities = result.objeto?.data?.activities!!
                        if (filterMonth <= 0)
                            activities.postValue(DataSession.activities)
                        else {
                            activities.postValue(
                                ArrayList(
                                    DataSession.activities.filter {
                                        it.age == filterMonth
                                    })
                            )
                        }
                    } else {
                        //TODO: perform no response actions
                    }
                }
                isLoading.postValue(false)
            }
        }
    }

    fun getArticles() {
        if (DataSession.articles.isNotEmpty()) {
            articles.postValue(DataSession.articles)
        } else {
            GlobalScope.launch {
                isLoading.postValue(true)
                val result = RetrofitHelper.getArticles(5)
                if (result.isError) {
                    // TODO perform error actions
                } else {
                    if (result.statusCode == 200 && result.objeto != null) {
                        DataSession.articles = result.objeto?.data?.articles!!
                        articles.postValue(result.objeto?.data?.articles)
                    } else {
                        //TODO perform no response actions
                    }
                }
                isLoading.postValue(false)
            }
        }
    }

}