package net.devrob.kinedut.api.response

import com.google.gson.annotations.SerializedName
import net.devrob.kinedut.commons.Const.Params.ACTIVITIES
import net.devrob.kinedut.commons.Const.Params.ID
import net.devrob.kinedut.commons.Const.Params.NAME
import net.devrob.kinedut.commons.Const.Params.TYPE
import net.devrob.kinedut.models.Activity

data class DataResponse(
    @SerializedName(ID)
    var id: Int,
    @SerializedName(NAME)
    var name: String,
    @SerializedName(TYPE)
    var type: String,
    @SerializedName(ACTIVITIES)
    var activities: ArrayList<Activity>
)