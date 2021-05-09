package com.sherryyuan.tictactoe

data class GameViewState (
    val info: String = "Next player: X",
    private var turn: Turn = Turn.X_PLAYER,
    val squares: List<String> = List(9) { "" }
) {

    fun updateState(index: Int): GameViewState {
        val updatedTurn = when (turn) {
            Turn.X_PLAYER -> Turn.Y_PLAYER
            Turn.Y_PLAYER -> Turn.X_PLAYER
        }

        val updatedInfo = "Next player: ${updatedTurn.label}"
        val updatedSquares = squares.toMutableList()
        updatedSquares[index] = turn.label
        return GameViewState(
            info = updatedInfo,
            turn = updatedTurn,
            squares = updatedSquares
        )
    }
}

enum class Turn(val label: String) {
    X_PLAYER("X"),
    Y_PLAYER("Y")
}