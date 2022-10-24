package me.blinq.apps.challenge.feature.invite

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.blinq.apps.challenge.designsystem.theme.BlinqTheme
import me.blinq.apps.challenge.feature.invitation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NameInput(
    modifier: Modifier = Modifier,
    name: String?,
    isError: Boolean = false,
    onNameChanged: (name: String) -> Unit,
    onNextClicked: () -> Unit
) {
    Column {
        TextField(
            modifier = modifier,
            value = name ?: "",
            onValueChange = {
                onNameChanged(it)
            },
            singleLine = true,
            isError = isError,
            label = {
                Text(
                    text = stringResource(
                        id =
                        R.string.label_full_name
                    )
                )
            },
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Person,
//                    contentDescription = null
//                )
//            },
            trailingIcon = {
                if (isError) {
                    Icon(Icons.Filled.Error,"error", tint = MaterialTheme.colorScheme.error)
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    onNextClicked()
                }
            )
        )
        AnimatedVisibility(isError) {
            Text(
                text = stringResource(id = R.string.error_name_at_least_three_characters),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_NameInput() {
    BlinqTheme {
        NameInput(
            name = "Mike Tran",
            onNameChanged = {},
            onNextClicked = {}
        )
    }
}