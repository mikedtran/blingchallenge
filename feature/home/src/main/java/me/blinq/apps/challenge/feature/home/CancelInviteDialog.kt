package me.blinq.apps.challenge.feature.home

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun CancelInviteDialog(
    modifier: Modifier = Modifier,
    message: String,
    dismissCancelInvite: () -> Unit,
    confirmCancelInvite: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            dismissCancelInvite()
        },
        dismissButton = {
            TextButton(onClick = {
                dismissCancelInvite()
            }) {
                Text(text = stringResource(id = R.string.cancel_invite_dismiss))
            }
        },
        confirmButton = {
            TextButton(onClick = {
                confirmCancelInvite()
            }) {
                Text(text = stringResource(id = R.string.cancel_invite_action))
            }
        },
        text = {
            Text(text = message)
        }
    )
}