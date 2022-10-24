package me.blinq.apps.challenge.feature.invite

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import me.blinq.apps.challenge.designsystem.theme.BlinqTheme
import me.blinq.apps.challenge.feature.invitation.R

@Composable
internal fun SendButton(
    modifier: Modifier = Modifier,
    enableInvite: Boolean,
    onSend: () -> Unit
) {
    Button(
        modifier = modifier,
        enabled = enableInvite,
        onClick = {
            onSend()
        }
    ) {
        Text(text = stringResource(id = R.string.action_send))
    }
}

@Preview
@Composable
private fun Preview_InviteButton() {
    BlinqTheme {
        SendButton(
            modifier = Modifier.fillMaxWidth(),
            enableInvite = true,
            onSend = {}
        )
    }
}