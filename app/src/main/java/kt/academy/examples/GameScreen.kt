package kt.academy.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class GameUiState(
    val level: Int = 1,
    val livesLeft: Int = 3,
    val code: String = "print()",
    val currentAnswer: String = "",
    val blocks: List<String> = listOf("println", "\"Hello\"", "(", ")")
)

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState

    fun selectBlock(block: String) {
        _uiState.update { state ->
            state.copy(currentAnswer = state.currentAnswer + block)
        }
    }
}

@Composable
fun GameScreen(vm: GameViewModel) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Header(uiState.level, uiState.livesLeft)
        Code(uiState.code, uiState.currentAnswer)
        SelectAnswer(uiState.blocks, vm::selectBlock)
    }
}

@Composable
fun Header(level: Int, livesLeft: Int) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Level(level)
        Hearts(livesLeft)
    }
}

@Composable
fun Level(level: Int) {
    Text("Level $level")
}

@Composable
fun Hearts(livesLeft: Int) {
    val hearts = "❤".repeat(livesLeft.coerceAtLeast(0))
    Text("Lives: $hearts")
}

@Composable
fun Code(code: String, currentAnswer: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Code: $code")
        Text("Answer: $currentAnswer")
    }
}

@Composable
fun SelectAnswer(blocks: List<String>, onSelect: (String) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        blocks.forEach { block ->
            Button(onClick = { onSelect(block) }) {
                Text(block)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen(vm = GameViewModel())
}