package com.sherryyuan.tictactoe

data class GameViewState(
    val info: String = "Next player: X",
    private var turn: Turn = Turn.X_PLAYER,
    val isFinished: Boolean = false,
    val squares: List<String> = List(9) { "" },
) {

    fun selectSquare(index: Int): GameViewState {
        val updatedSquares = squares.toMutableList()
        updatedSquares[index] = turn.label

        val updatedTurn = when (turn) {
            Turn.X_PLAYER -> Turn.Y_PLAYER
            Turn.Y_PLAYER -> Turn.X_PLAYER
        }

        val winner = calculateWinner(updatedSquares)

        val updatedInfo = if (winner == null) {
            "Next player: ${updatedTurn.label}"
        } else {
            "Winner: $winner"
        }
        return GameViewState(
            info = updatedInfo,
            turn = updatedTurn,
            isFinished = winner != null,
            squares = updatedSquares,
        )
    }

    private fun calculateWinner(squares: List<String>): String? {
        val winningLines = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6),
        )
        winningLines.forEach { lines ->
            if (squares[lines[0]].isNotBlank() &&
                squares[lines[0]] == squares[lines[1]] &&
                squares[lines[0]] == squares[lines[2]]
            ) {
                return squares[lines[0]]
            }
        }
        return null
    }
}

enum class Turn(val label: String) {
    X_PLAYER("X"),
    Y_PLAYER("Y")
}