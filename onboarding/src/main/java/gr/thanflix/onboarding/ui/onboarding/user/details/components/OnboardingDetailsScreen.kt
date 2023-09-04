package gr.thanflix.onboarding.ui.onboarding.user.details.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import gr.thanflix.onboarding.ui.onboarding.user.details.interactors.OnboardingDetailsEvents
import gr.thanflix.onboarding.ui.onboarding.user.details.interactors.OnboardingDetailsState
import gr.thanflix.presentation.R
import gr.thanflix.presentation.components.ThanflixAppBar
import gr.thanflix.presentation.components.compose.ThanflixTextField

@Composable
fun OnboardingDetailsScreen(
    state: OnboardingDetailsState,
    onTriggerEvent: (OnboardingDetailsEvents) -> Unit
) {

    var name by remember { mutableStateOf("") }
    var surename by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

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
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ThanflixTextField(
                value = name,
                placeholder = stringResource(id = R.string.NAME_FIELD_PLACEHOLDER),
                onValueChange = { name = it },
                modifier = Modifier
                    .padding(horizontal = 24.dp) // margin left and right
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            ThanflixTextField(
                value = surename,
                placeholder = stringResource(id = R.string.SURNAME_FIELD_PLACEHOLDER),
                onValueChange = { surename = it },
                modifier = Modifier
                    .padding(horizontal = 24.dp) // margin left and right
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            ThanflixTextField(
                value = phoneNumber,
                placeholder = stringResource(id = R.string.PHONENUMBER_FIELD_PLACEHOLDER),
                onValueChange = { phoneNumber = it },
                modifier = Modifier
                    .padding(horizontal = 24.dp) // margin left and right
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 24.dp),
                onClick = { onTriggerEvent.invoke(OnboardingDetailsEvents.NextStep(name, surename, phoneNumber)) }) {
                Text(text = stringResource(id = R.string.REGISTER_CTA_TITLE))
            }
        }
    }
}

