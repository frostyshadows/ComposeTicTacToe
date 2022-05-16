package com.sherryyuan.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome To TicTacToe",
            fontSize = LocalConfiguration.current.fontScale.times(25).sp,
            fontWeight = FontWeight.Bold,
            color = Black
        )
        Spacer(modifier = Modifier.padding(LocalConfiguration.current.screenHeightDp.dp/30))
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = state.info,
            color = Black,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
        Board(
            squares = state.squares,
            isBoardEnabled = !state.isFinished,
            onClick = ::onSquareSelected,
        )
        if (state.isFinished) {
            Button(
                modifier = Modifier.padding(top = 16.dp),
                border = BorderStroke(1.dp, Black),
                onClick = ::onPlayAgainClicked,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Cyan,
                )
            ) {
                Text(
                    text = "Play again",
                    color = Black,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.button,
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun Board(squares: List<String>, isBoardEnabled: Boolean, onClick: (Int) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
    ) {
        items(squares.size) { index ->
            Square(squares[index], isBoardEnabled) { onClick(index) }
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
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = Cyan
        )
    ) {
        Text(
            text = selection,
            color = Black,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold
        )
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
