package net.devrob.kinedut.models

class RequestResponse<T> {
    var objeto: T? = null
    var statusCode: Int = 0
    var isError = false
    var throwable: Throwable? = null
}