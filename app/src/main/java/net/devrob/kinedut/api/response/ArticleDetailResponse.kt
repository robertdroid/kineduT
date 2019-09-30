package net.devrob.kinedut.api.response

import com.google.gson.annotations.SerializedName
import net.devrob.kinedut.commons.Const
import net.devrob.kinedut.models.Article

data class ArticleDetailResponse(
    @SerializedName(Const.Params.DATA)
    var data: DataResponse<Article>
)