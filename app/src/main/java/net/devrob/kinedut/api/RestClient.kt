package net.devrob.kinedut.api

import net.devrob.kinedut.api.response.ActivitiesResponse
import net.devrob.kinedut.api.response.ArticleDetailResponse
import net.devrob.kinedut.api.response.ArticleReponse
import net.devrob.kinedut.commons.Const.Endpoints.ACTIVITIES
import net.devrob.kinedut.commons.Const.Endpoints.ARTICLES
import net.devrob.kinedut.commons.Const.Endpoints.ARTICLE_DETAIL
import net.devrob.kinedut.commons.Const.Params.AUTHORIZATION
import net.devrob.kinedut.commons.Const.Params.BABY_ID
import net.devrob.kinedut.commons.Const.Params.ID
import net.devrob.kinedut.commons.Const.Params.SKILL_ID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface RestClient {
    @GET(ACTIVITIES)
    fun getActivities(
        @Header(AUTHORIZATION) auth: String,
        @Query(SKILL_ID) skillId: Int,
        @Query(BABY_ID) babyId: Int): Call<ActivitiesResponse>

    @GET(ARTICLES)
    fun getArticles(
        @Header(AUTHORIZATION) auth: String,
        @Query(SKILL_ID) skillId: Int,
        @Query(BABY_ID) babyId: Int): Call<ArticleReponse>

    @GET(ARTICLE_DETAIL)
    fun getArticleDetail(
        @Header(AUTHORIZATION) auth: String,
        @Path(ID) id: Int): Call<ArticleDetailResponse>
}