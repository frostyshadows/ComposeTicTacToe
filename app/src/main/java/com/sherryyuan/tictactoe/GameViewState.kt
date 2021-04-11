package com.sherryyuan.tictactoe

data class GameViewState(
    val info: String = "",
    val turn: Turn = Turn.X_PLAYER,
    val squares: List<String?> = List(9) { null }
)

enum class Turn(val displayText: String) {
    X_PLAYER("Next player: X"),
    Y_PLAYER("Next player:Y")
}