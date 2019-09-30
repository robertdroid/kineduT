package net.devrob.kinedut.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.devrob.kinedut.api.RetrofitHelper
import net.devrob.kinedut.models.Article

class ArticleViewModel : BaseViewModel() {
    private val articleLD = MutableLiveData<Article>()
    val infoArticle: LiveData<Article> = Transformations.map(articleLD) {
        articleLD.value
    }

    fun updateIfNecesaryArticle(article: Article) {
        if (article.title.isNullOrBlank() || article.body.isNullOrBlank()) {
            GlobalScope.launch {
                isLoading.postValue(true)
                val result = RetrofitHelper.getArticleDetail(article.id)
                if (result.isError) {
                    //TODO perform error actions
                } else {
                    if (result.statusCode == 200 && result.objeto != null) {
                        val artResult = result.objeto?.data?.article
                        article.body = artResult!!.body
                        article.link = artResult.link
                        article.title = artResult.title
                        articleLD.postValue(article)
                    }
                }
                isLoading.postValue(false)
            }
        } else {
            articleLD.postValue(article)
        }
    }
}