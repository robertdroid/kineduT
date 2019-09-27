package net.devrob.kinedut.commons

data class SpinnerObject(
    var id: Int = -1,
    var value: String = ""
) {
    override fun toString(): String {
        return value
    }
}