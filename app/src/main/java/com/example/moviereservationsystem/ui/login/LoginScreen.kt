package com.example.moviereservationsystem.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviereservationsystem.R
import com.example.moviereservationsystem.ui.theme.MovieReservationSystemTheme
import com.example.moviereservationsystem.ui.theme.onPrimaryContainerLight
import com.example.moviereservationsystem.ui.theme.outlineVariantLightMediumContrast
import com.example.moviereservationsystem.ui.theme.primaryContainerLight
import com.example.moviereservationsystem.ui.theme.primaryLight
import com.example.moviereservationsystem.ui.theme.tertiaryLight

@Composable
fun LoginScreen(loginViewModel: LoginViewModel){
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    )
    {
        Login(Modifier.align(Alignment.Center),loginViewModel)

    }

}

@Composable
fun Login(modifier: Modifier,loginViewModel: LoginViewModel){

    val loginUiState by loginViewModel.uiState.collectAsState()

    Column(modifier = modifier) {
        HeaderContent(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        EmailField(
            email = loginUiState.email,
            onTextFieldChanged = { loginViewModel.updateEmail(it)}
        )
        Spacer(modifier = Modifier.padding(16.dp))
        PasswordField(
            password = loginUiState.password,
            onTextFieldChanged = { loginViewModel.updatePassword(it) }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        SignUpRedirection()
        Spacer(modifier = Modifier.padding(16.dp))
        LogInButton(
            isEnabled = loginUiState.isLoginEnabled,
            isLoading = loginUiState.isLoading,
            onClick = { loginViewModel.login() }
        )
    }
}

@Composable
fun HeaderContent(modifier: Modifier) {
    Image(
        painterResource(id = R.drawable.movieticketicon),
        contentDescription = "Header",
        modifier = modifier
    )
    Spacer(modifier = Modifier.padding(8.dp))
    Text(
        text = "Movie Reservation System",
        style = MaterialTheme.typography.labelLarge,
        modifier = modifier
    )
    Spacer(modifier = Modifier.padding(16.dp))
    Text(
        text = "Log In",
        style = MaterialTheme.typography.titleLarge,
        color = primaryLight,
        modifier = modifier)
}

@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit){
    OutlinedTextField(
        value = email,
        onValueChange = { onTextFieldChanged(it) },
        label = {Text(text = "Enter your Email")},
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            autoCorrect = false
        ),
        singleLine = true,
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = onPrimaryContainerLight,
            focusedLabelColor = onPrimaryContainerLight

        )
    )
}

@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit){

    OutlinedTextField(
        value = password,
        onValueChange = { onTextFieldChanged(it) },
        label = {Text(text = "Enter your Password")},
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = onPrimaryContainerLight,
            focusedLabelColor = onPrimaryContainerLight
        )
    )
}

@Composable
fun SignUpRedirection(){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Don't have any acount?",
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.padding(start = 4.dp))
        Text(
            text = "Register",
            style = MaterialTheme.typography.labelLarge,
            color = tertiaryLight
        )
    }
}


@Composable
fun LogInButton(isEnabled:Boolean,isLoading:Boolean,onClick: () -> Unit){
    Button(
        onClick = {onClick()},
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
                containerColor = primaryContainerLight,
                contentColor = outlineVariantLightMediumContrast
        )
    ) {
        Text(
            text = "Log In",
            style = MaterialTheme.typography.labelLarge
        )
    }

}


