package me.blinq.apps.challenge.feature.invite

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.blinq.apps.challenge.feature.invitation.R

@Composable
internal fun InviteTitle() {
    Text(
        text = stringResource(id = R.string.label_request_invite),
        fontSize = 24.sp,
        fontWeight = FontWeight.Black
    )
}

