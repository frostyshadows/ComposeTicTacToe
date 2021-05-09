package com.sherryyuan.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sherryyuan.tictactoe.ui.theme.TicTacToeTheme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Game()
                }
            }
        }
    }
}

@Composable
fun Square(selection: String, isBoardEnabled: Boolean, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp),
        border = BorderStroke(1.dp, Black),
        shape = RectangleShape,
        enabled = isBoardEnabled && selection.isBlank(),
        onClick = onClick,
    ) {
        Text(
            text = selection,
            color = Black,
        )
    }
}

@Composable
fun PlayAgain(onClick: () -> Unit) {
    Button(
        onClick = onClick
    ) {
        Text(
            text = "Play again",
            color = Black,
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun Board(squares: List<String>, isBoardEnabled: Boolean, onClick: (Int) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3)
    ) {
        items(squares.size) { index ->
            Square(squares[index], isBoardEnabled) { onClick(index) }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun Game() {
    var state by remember { mutableStateOf(GameViewState()) }

    fun onSquareSelected(index: Int) {
        state = state.selectSquare(index)
    }

    fun onPlayAgainClicked() {
        state = GameViewState()
    }

    Column {
        Text(text = state.info, color = Black)
        Board(
            squares = state.squares,
            isBoardEnabled = !state.isFinished,
            onClick = ::onSquareSelected,
        )
        if (state.isFinished) {
            PlayAgain(
                onClick = ::onPlayAgainClicked
            )
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TicTacToeTheme {
        Game()
    }
}
