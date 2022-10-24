package me.blinq.apps.challenge.feature.invite

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import me.blinq.apps.challenge.designsystem.theme.BlinqTheme
import me.blinq.apps.challenge.feature.invitation.R

@Composable
fun InviteErrorDialog(
    modifier: Modifier = Modifier,
    error: String,
    dismissError: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            dismissError()
        },
        confirmButton = {
            TextButton(onClick = {
                dismissError()
            }) {
                Text(text = stringResource(id = R.string.error_action))
            }
        },
        title = {
            Text(text = stringResource(id = R.string.error_title), fontSize = 18.sp)
        },
        text = {
            Text(text = error)
        }
    )
}

@Preview
@Composable
fun Preview_AuthenticationErrorDialog() {
    BlinqTheme {
        InviteErrorDialog(
            error = "There was an error!",
            dismissError = { }
        )
    }
}
