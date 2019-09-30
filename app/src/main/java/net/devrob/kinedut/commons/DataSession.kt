package net.devrob.kinedut.commons

import net.devrob.kinedut.models.Activity
import net.devrob.kinedut.models.Article

object DataSession {
    //TODO: Hardcode to test only, get the real token and baby id from session
    var sessionToken = "5105f4358e45f6f98057a654c882b7742c3ac5241c81a706acc48c84f8acde9f8a344993ac42369627ae9f2caf1eed42ff1be9562fe2167c9c80908e76e95c49"
    var sessionBabyId = 2064732

    var activities: ArrayList<Activity> = ArrayList()
    var articles: ArrayList<Article> = ArrayList()
}