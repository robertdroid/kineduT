package net.devrob.kinedut.api.response

import com.google.gson.annotations.SerializedName
import net.devrob.kinedut.commons.Const.Params.DATA
import net.devrob.kinedut.models.Article

data class ArticleReponse (
    @SerializedName(DATA)
    var data: DataResponse<Article>
)