package net.devrob.kinedut.models

import com.google.gson.annotations.SerializedName
import net.devrob.kinedut.commons.Const.Params.ID
import net.devrob.kinedut.commons.Const.Params.MAX_AGE
import net.devrob.kinedut.commons.Const.Params.MIN_AGE
import net.devrob.kinedut.commons.Const.Params.NAME
import net.devrob.kinedut.commons.Const.Params.PICTURE
import net.devrob.kinedut.commons.Const.Params.SHORT_DESCRIPTION
import net.devrob.kinedut.commons.Const.Params.TYPE
data class Article(
    @SerializedName(ID)
    var id: Int,
    @SerializedName(TYPE)
    var type: String,
    @SerializedName(NAME)
    var name: String,
    @SerializedName(MIN_AGE)
    var min_age: Int,
    @SerializedName(MAX_AGE)
    var max_age: Int,
    @SerializedName(PICTURE)
    var picture: String,
    @SerializedName(SHORT_DESCRIPTION)
    var shortDescription: String
)