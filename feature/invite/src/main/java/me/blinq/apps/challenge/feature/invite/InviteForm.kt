package me.blinq.apps.challenge.feature.invite

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.blinq.apps.challenge.designsystem.theme.BlinqTheme

@Composable
internal fun InviteForm(
    modifier: Modifier = Modifier,
    name: String?,
    isNameError: Boolean = false,
    onNameChanged: (name: String) -> Unit,
    email: String?,
    isEmailError: Boolean = false,
    onEmailChanged: (email: String) -> Unit,
    confirmEmail: String?,
    isConfirmEmailError: Boolean = false,
    onConfirmEmailChanged: (email: String) -> Unit,
    onSend: () -> Unit
){
    val emailFocusRequester = remember {
        FocusRequester()
    }

    val confirmEmailFocusRequester = remember {
        FocusRequester()
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier.scrollable(
            state = scrollState,
            orientation = Orientation.Vertical
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        InviteTitle()
        Spacer(modifier = Modifier.height(40.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment =
                Alignment.CenterHorizontally
            ) {

                NameInput(
                    modifier = Modifier.fillMaxWidth(),
                    name = name ?: "",
                    isError = isNameError,
                    onNameChanged = onNameChanged
                ) {
                    emailFocusRequester.requestFocus()
                }
                Spacer(modifier = Modifier.height(16.dp))
                EmailInput(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(emailFocusRequester),
                    email = email,
                    isError = isEmailError,
                    onEmailChanged = onEmailChanged
                ) {
                    confirmEmailFocusRequester.requestFocus()
                }
                Spacer(modifier = Modifier.height(16.dp))
                ConfirmEmailInput(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(confirmEmailFocusRequester),
                    email = confirmEmail,
                    isError = isConfirmEmailError,
                    onEmailChanged = onConfirmEmailChanged
                ) {
                    onSend()
                }
                Spacer(modifier = Modifier.height(12.dp))
                SendButton(
                    enableInvite = true,
                    onSend = onSend
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_InviteForm() {
    BlinqTheme {
        InviteForm(
            modifier = Modifier.fillMaxWidth(),
            name = "Mike Tran",
            onNameChanged = {},
            email = "mike.d.tran@outlook.com",
            onEmailChanged = {},
            confirmEmail = "mike.d.tran@outlook.com",
            onConfirmEmailChanged = {},
            onSend = {}
        )
    }
}

