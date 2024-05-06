package at.fhv.jeb

import at.fhv.jeb.objects.Entity
import at.fhv.jeb.objects.GameState

fun main() {
    val initialState = GameState()

    val closeList = mutableSetOf(initialState)
    val openList = mutableListOf(initialState)
    val validResults = mutableListOf<GameState>()

    while (openList.isNotEmpty()) {
        val current = openList.removeFirst()

        getSuccessor(current).forEach { successor ->
            if (!closeList.contains(successor)) {
                closeList.add(successor)

                if (successor.isValid()) {
                    openList.add(successor) // BFS
                    // openList.addFirst(successor) // DFS
                }

                if (successor.isFinished()) {
                    validResults.add(successor)
                }
            }
        }
    }

    validResults.forEach(GameState::printAll)
}

fun getSuccessor(current: GameState): List<GameState> {
    return Entity.entries.mapNotNull { entity ->
        current.switchSide(entity)
    }
}

