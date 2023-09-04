package gr.thanflix.onboarding.ui.authorization.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import gr.thanflix.onboarding.ui.authorization.interactors.LoginEvents
import gr.thanflix.onboarding.ui.authorization.interactors.LoginState
import gr.thanflix.presentation.R
import gr.thanflix.presentation.components.ThanflixAppBar

@Composable
fun LoginScreen(
    state: LoginState,
    onTriggerEvent: (LoginEvents) -> Unit
) {

    Column {

        // header
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            factory = {
                ThanflixAppBar(it, null).apply {
                    primaryTitle = it.getString(R.string.AUTHORIZATION_TITLE)
                    foregroundColor = R.color.tint_primary
                    noNavAction = true
                }
            }
        )

        // body
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 24.dp),
                onClick = { onTriggerEvent.invoke(LoginEvents.GoToMainApp) }) {
                Text(text = stringResource(id = R.string.LOGIN_CTA_TITLE))
            }

            Spacer(modifier = Modifier.height(56.dp))
            
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 24.dp),
                onClick = { onTriggerEvent.invoke(LoginEvents.GoToOnboarding) }) {
                Text(text = stringResource(id = R.string.REGISTER_CTA_TITLE))
            }
        }
    }

}