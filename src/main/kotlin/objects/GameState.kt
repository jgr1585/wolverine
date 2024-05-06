package at.fhv.jeb.objects

data class GameState(
    val gameObjects: Map<Entity, Side> = Entity.entries.associateWith { Side.LEFT },
    val parent: GameState? = null
) {
    fun isValid(): Boolean {
        val farmerSide = gameObjects[Entity.FARMER] ?: return false
        val wolfSide = gameObjects[Entity.WOLF] ?: return false
        val goatSide = gameObjects[Entity.GOAT] ?: return false
        val cabbageSide = gameObjects[Entity.CABBAGE] ?: return false

        if (farmerSide != wolfSide && wolfSide == goatSide) {
            return false
        }
        if (farmerSide != goatSide && goatSide == cabbageSide) {
            return false
        }
        return true
    }

    //Returns Null if step is Invalid
    fun switchSide(entity: Entity): GameState? {
        if (gameObjects[Entity.FARMER] != gameObjects[entity]) {
            return null
        }

        val newGameObjects = gameObjects.toMutableMap()

        //Switch Side
        newGameObjects[entity] = gameObjects[entity]?.opposite() ?: return null
        newGameObjects[Entity.FARMER] = gameObjects[Entity.FARMER]?.opposite() ?: return null

        return GameState(newGameObjects, this)
    }

    fun isFinished(): Boolean {
        return gameObjects.all { it.value == Side.RIGHT }
    }

    fun printAll() {
        var current: GameState? = this
        var printable = ""

        while (current != null) {
            printable = "${current.gameObjects}\n$printable"
            current = current.parent
        }

        print(printable)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GameState) return false

        if (gameObjects != other.gameObjects) return false

        return true
    }

    override fun hashCode(): Int {
        return gameObjects.hashCode()
    }


}
