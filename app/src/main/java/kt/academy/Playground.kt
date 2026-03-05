import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview

@Stable
interface SearchBarState {
    val query: String
    val isExpanded: Boolean
    val suggestions: List<String>

    fun updateQuery(newQuery: String)
    fun clearQuery()
    fun submit()
    fun expand()
    fun collapse()
    fun toggle()
    fun selectSuggestion(suggestion: String)
}

private class SearchBarStateImpl(
    private val suggester: (String) -> List<String>,
    initialQuery: String,
    initialExpanded: Boolean,
    private val maxSuggestions: Int
) : SearchBarState {
    private var _query by mutableStateOf(initialQuery)
    private var _expanded by mutableStateOf(initialExpanded)

    override val query: String get() = _query
    override val isExpanded: Boolean get() = _expanded
    override val suggestions: List<String> by derivedStateOf {
        if (_query.isBlank()) emptyList() else suggester(_query).take(maxSuggestions)
    }

    override fun updateQuery(newQuery: String) {
        if (newQuery != _query) {
            _query = newQuery
            _expanded = true
        }
    }

    override fun clearQuery() {
        _query = ""
        _expanded = false
    }

    override fun submit() {
        _expanded = false
    }

    override fun expand() {
        _expanded = true
    }

    override fun collapse() {
        _expanded = false
    }

    override fun toggle() {
        _expanded = !_expanded
    }

    override fun selectSuggestion(suggestion: String) {
        _query = suggestion
        _expanded = false
    }
}

@Composable
fun rememberSearchBarState(
    initialQuery: String = "",
    initialExpanded: Boolean = false,
    maxSuggestions: Int = 8,
    suggester: (String) -> List<String> = { emptyList() }
): SearchBarState {
    return remember(suggester, initialQuery, initialExpanded, maxSuggestions) {
        SearchBarStateImpl(
            suggester = suggester,
            initialQuery = initialQuery,
            initialExpanded = initialExpanded,
            maxSuggestions = maxSuggestions
        )
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    state: SearchBarState = rememberSearchBarState(),
    placeholder: String = "Search…",
    onSubmit: (String) -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    Box(modifier = modifier) {
        TextField(
            value = state.query,
            onValueChange = { state.updateQuery(it) },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focus ->
                    if (focus.isFocused) state.expand()
                },
            placeholder = { Text(placeholder) },
            singleLine = true,
            trailingIcon = {
                Row {
                    if (state.query.isNotEmpty()) {
                        IconButton(onClick = { state.clearQuery() }) {
                            Icon(Icons.Filled.Close, contentDescription = "Clear")
                        }
                    }
                    IconButton(onClick = { state.toggle() }) {
                        Icon(
                            imageVector = if (state.isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            contentDescription = if (state.isExpanded) "Collapse" else "Expand"
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSubmit(state.query)
                    state.submit()
                    focusManager.clearFocus()
                }
            ),
            colors = TextFieldDefaults.colors()
        )

        DropdownMenu(
            expanded = state.isExpanded && state.suggestions.isNotEmpty(),
            onDismissRequest = { state.collapse() },
        ) {
            state.suggestions.forEach { suggestion ->
                DropdownMenuItem(
                    text = { Text(suggestion) },
                    onClick = {
                        state.selectSuggestion(suggestion)
                        onSubmit(suggestion)
                        focusManager.clearFocus()
                    },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    SearchBar(
        state = rememberSearchBarState(initialQuery = "Hello", initialExpanded = true, maxSuggestions = 3) { query ->
            listOf("Hello World", "Hello Android", "Hello Jetpack Compose", "Hello Kotlin")
        },
        placeholder = "Search",
        modifier = Modifier.fillMaxSize()
    )
}