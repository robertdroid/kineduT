package net.devrob.kinedut.commons

object Const {
    const val BASE_URL = "http://staging.kinedu.com/api/v3/"
    private const val CATALOGUE = "${BASE_URL}catalogue/"
    const val TOKEN_VALUE = "Token token=%s"

    object Endpoints {
        const val ACTIVITIES = "${CATALOGUE}activities"
        const val ARTICLES = "${CATALOGUE}articles/"
        const val ARTICLE_DETAIL = "${ARTICLES}{${Params.ID}}"
    }

    object Params {
        const val ID = "id"
        const val SKILL_ID = "skill_id"
        const val BABY_ID = "baby_id"
        const val AUTHORIZATION = "Authorization"

        const val NAME = "name"
        const val AGE = "age"
        const val AGE_GROUP = "age_group"
        const val PURPOSE = "purpose"
        const val THUMBNAIL = "thumbnail"
        const val ACTIVE_MILESTONES = "active_milestones"
        const val COMPLETED_MILESTONES = "completed_milestones"
        const val TYPE = "type"
        const val ACTIVITIES = "activities"
        const val DATA = "data"
        const val MIN_AGE = "min_age"
        const val MAX_AGE = "max_age"
        const val PICTURE = "picture"
        const val SHORT_DESCRIPTION = "short_description"
        const val ARTICLES = "articles"
    }
}