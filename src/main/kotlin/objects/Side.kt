package at.fhv.jeb.objects

enum class Side {
    LEFT, RIGHT;

    fun opposite(): Side {
        return when (this) {
            LEFT -> RIGHT
            RIGHT -> LEFT
        }
    }
}