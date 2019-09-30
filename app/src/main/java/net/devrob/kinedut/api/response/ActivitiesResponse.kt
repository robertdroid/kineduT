package net.devrob.kinedut.api.response

import com.google.gson.annotations.SerializedName
import net.devrob.kinedut.commons.Const.Params.DATA

data class ActivitiesResponse(
    @SerializedName(DATA)
    var data: DataResponse
)