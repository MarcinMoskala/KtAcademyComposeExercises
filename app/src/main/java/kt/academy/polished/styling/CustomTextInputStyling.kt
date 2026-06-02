package kt.academy.polished.styling

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kt.academy.polished.theme.AppTheme

@Composable
fun AccountAccessScreen(
    state: AccountAccessState,
    onHandleChange: (String) -> Unit,
    onAccessTokenChange: (String) -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .padding(AppTheme.sizes.screenPadding),
        verticalArrangement = Arrangement.spacedBy(AppTheme.sizes.md),
    ) {
        Text(
            text = "Account access",
            style = AppTheme.typography.titleLarge,
            color = AppTheme.colors.textPrimary,
        )

        // TODO: Replace with HandleTextInput built on top of AppTextInput.
        HandleTextInputStarter(
            value = state.handle,
            onValueChange = onHandleChange,
            isError = state.handleError != null,
            supportingText = state.handleError ?: "This handle will be visible to other learners.",
            modifier = Modifier.fillMaxWidth(),
        )

        // TODO: Replace with AccessTokenTextInput built on top of AppTextInput.
        AccessTokenTextInputStarter(
            value = state.accessToken,
            onValueChange = onAccessTokenChange,
            isError = state.accessTokenError != null,
            supportingText = state.accessTokenError ?: "Used only to connect your learning workspace.",
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onSave,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = AppTheme.sizes.buttonHeight),
        ) {
            Text("Save")
        }
    }
}

@Composable
private fun HandleTextInputStarter(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    supportingText: String? = null,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = "Public handle",
            color = if (isError) Color.Red else Color.Gray,
            fontSize = 14.sp,
        )

        // TODO: Move shared input styling into AppTextInput.
        // TODO: Use AppTextInputDefaults instead of hardcoded colors and padding.
        // TODO: Add focused, unfocused, error, and disabled border colors.
        // TODO: Add error semantics when isError is true.
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 56.dp)
                        .background(Color.White, RoundedCornerShape(16.dp))
                        .border(
                            width = 1.dp,
                            color = if (isError) Color.Red else Color.LightGray,
                            shape = RoundedCornerShape(16.dp),
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = "@compose_dev",
                            color = Color.Gray,
                            fontSize = 16.sp,
                        )
                    }
                    innerTextField()
                }
            },
        )

        if (supportingText != null) {
            Text(
                text = supportingText,
                color = if (isError) Color.Red else Color.Gray,
                fontSize = 12.sp,
            )
        }
    }
}

@Composable
private fun AccessTokenTextInputStarter(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    supportingText: String? = null,
) {
    var visible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = "Access token",
            color = if (isError) Color.Red else Color.Gray,
            fontSize = 14.sp,
        )

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
            ),
            visualTransformation = if (visible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 56.dp)
                        .background(Color.White, RoundedCornerShape(16.dp))
                        .border(
                            width = 1.dp,
                            color = if (isError) Color.Red else Color.LightGray,
                            shape = RoundedCornerShape(16.dp),
                        )
                        .padding(start = 16.dp, end = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (value.isEmpty()) {
                            Text(
                                text = "Enter token",
                                color = Color.Gray,
                                fontSize = 16.sp,
                            )
                        }
                        innerTextField()
                    }

                    // TODO: Keep this as an icon-only action with a meaningful contentDescription.
                    IconButton(onClick = { visible = !visible }) {
                        Icon(
                            imageVector = if (visible) {
                                Icons.Outlined.VisibilityOff
                            } else {
                                Icons.Outlined.Visibility
                            },
                            contentDescription = null,
                        )
                    }
                }
            },
        )

        if (supportingText != null) {
            Text(
                text = supportingText,
                color = if (isError) Color.Red else Color.Gray,
                fontSize = 12.sp,
            )
        }
    }
}

// Hide

@Preview
@Composable
private fun AccountAccessScreenPreview() {
    AppTheme {
        AccountAccessScreen(
            state = AccountAccessState(
                handle = "",
                accessToken = "secret-token",
            ),
            onHandleChange = {},
            onAccessTokenChange = {},
            onSave = {},
        )
    }
}

@Preview(fontScale = 1.4f)
@Composable
private fun AccountAccessScreenErrorPreview() {
    AppTheme {
        AccountAccessScreen(
            state = AccountAccessState(
                handleError = "Handle must start with @.",
                accessTokenError = "Token is too short.",
            ),
            onHandleChange = {},
            onAccessTokenChange = {},
            onSave = {},
        )
    }
}

data class AccountAccessState(
    val handle: String = "",
    val accessToken: String = "",
    val handleError: String? = null,
    val accessTokenError: String? = null,
)