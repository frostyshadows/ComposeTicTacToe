package com.sherryyuan.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color.Companion.Black
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
                    Game(GameViewState())
                }
            }
        }
    }
}

@Composable
fun Square(selection: String?) {
    Button(
        onClick = {},
        border = BorderStroke(1.dp, Black),
    ) {
        Text(text = selection.orEmpty())
    }
}

@ExperimentalFoundationApi
@Composable
fun Board(squares: List<String?>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3)
    ) {
        itemsIndexed(squares) { _, selection ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Square(selection)
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun Game(state: GameViewState) {
    Column {
        Text(text = state.info)
        Board(squares = state.squares)
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TicTacToeTheme {
        Game(GameViewState())
    }
}

const val X = "X"
const val O = "O"