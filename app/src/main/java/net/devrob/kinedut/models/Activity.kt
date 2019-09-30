package net.devrob.kinedut.models

import com.google.gson.annotations.SerializedName
import net.devrob.kinedut.commons.Const.Params.ACTIVE_MILESTONES
import net.devrob.kinedut.commons.Const.Params.AGE
import net.devrob.kinedut.commons.Const.Params.AGE_GROUP
import net.devrob.kinedut.commons.Const.Params.COMPLETED_MILESTONES
import net.devrob.kinedut.commons.Const.Params.ID
import net.devrob.kinedut.commons.Const.Params.NAME
import net.devrob.kinedut.commons.Const.Params.PURPOSE
import net.devrob.kinedut.commons.Const.Params.THUMBNAIL

data class Activity (
    @SerializedName(ID)
    var id: Int,
    @SerializedName(NAME)
    var name: String,
    @SerializedName(AGE)
    var age: Int,
    @SerializedName(AGE_GROUP)
    var age_group: String,
    @SerializedName(PURPOSE)
    var purpose: String,
    @SerializedName(THUMBNAIL)
    var thumbnail: String,
    @SerializedName(ACTIVE_MILESTONES)
    var active_milestones: Int,
    @SerializedName(COMPLETED_MILESTONES)
    var completed_milestones: Int
)