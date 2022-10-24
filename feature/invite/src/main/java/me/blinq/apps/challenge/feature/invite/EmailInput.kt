package me.blinq.apps.challenge.feature.invite

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
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
internal fun EmailInput(
    modifier: Modifier = Modifier,
    email: String?,
    isError: Boolean = false,
    onEmailChanged: (email: String) -> Unit,
    onNextClicked: () -> Unit
) {
    Column {
        TextField(
            modifier = modifier,
            value = email ?: "",
            onValueChange = {
                onEmailChanged(it)
            },
            isError = isError,
            label = {
                Text(text = stringResource(id = R.string.label_email))
            },
            singleLine = true,
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Email,
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
                keyboardType = KeyboardType.Email
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    onNextClicked()
                }
            )
        )
        AnimatedVisibility(isError) {
            Text(
                text = stringResource(id = R.string.error_email_incorrect),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_EmailInput() {
    BlinqTheme {
        EmailInput(
            modifier = Modifier.fillMaxWidth(),
            email = "mike.d.tran@outlook.com",
            onEmailChanged = { },
            onNextClicked = {}
        )
    }
}