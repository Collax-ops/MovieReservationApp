package com.example.moviereservationsystem.ui.screens.signup

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moviereservationsystem.ui.navigation.AppDestination
import com.example.moviereservationsystem.ui.screens.login.LogInButton
import com.example.moviereservationsystem.ui.screens.theme.MovieReservationSystemTheme
import com.example.moviereservationsystem.ui.screens.theme.onPrimaryContainerLight
import com.example.moviereservationsystem.ui.screens.theme.outlineVariantLightMediumContrast
import com.example.moviereservationsystem.ui.screens.theme.primaryContainerLight
import com.example.moviereservationsystem.ui.screens.theme.primaryLight
import com.example.moviereservationsystem.ui.screens.theme.tertiaryLight

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel, navController: NavHostController) {
    val signUpUiState by signUpViewModel.uiState.collectAsState()

    LaunchedEffect(signUpUiState.navigateToLogin) {
        if (signUpUiState.navigateToLogin) {
            navController.navigate(AppDestination.Login.route) {
                popUpTo(AppDestination.SignUp.route) { inclusive = true }
            }
            signUpViewModel.onNavigationHandled()
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        SignUp(Modifier.align(Alignment.Center), signUpViewModel)
    }
}


@Composable
fun SignUp(modifier: Modifier,signUpViewModel: SignUpViewModel){

    val signUpUiState by signUpViewModel.uiState.collectAsState()

    Column (modifier = modifier){
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.titleLarge,
            color = primaryLight,
            modifier = modifier
        )
        Spacer(modifier = Modifier.padding(16.dp))
        NameField(
            name = signUpUiState.name,
            onTextFieldChange = {signUpViewModel.updateName(it)}
        )
        Spacer(modifier = Modifier.padding(16.dp))
        EmailField(
            email = signUpUiState.email,
            onTextFieldChange = {signUpViewModel.updateEmail(it)}
        )
        Spacer(modifier = Modifier.padding(16.dp))
        PasswordField(
            password = signUpUiState.password,
            onTextFieldChange = { signUpViewModel.updatePassword(it) }
        )
        Spacer(modifier = Modifier.padding(16.dp))
        SignUpButton(
            isSignUpEnabled = signUpUiState.isSignUpEnabled,
            isLoading = signUpUiState.isLoading,
            onClick = { signUpViewModel.signUp() }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        LogInRedirection()
    }

}


@Composable
fun NameField(name: String, onTextFieldChange: (String) -> Unit){


    OutlinedTextField(
        value = name,
        onValueChange = {onTextFieldChange(it)},
        label = {Text(text = "Enter your Name")},
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = onPrimaryContainerLight,
            focusedLabelColor = onPrimaryContainerLight

        )
    )
}

@Composable
fun EmailField(email: String, onTextFieldChange: (String) -> Unit){

    OutlinedTextField(
        value = email,
        onValueChange = {onTextFieldChange(it)},
        label = {Text(text = "Enter your Email")},
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = onPrimaryContainerLight,
            focusedLabelColor = onPrimaryContainerLight

        )
    )
}

@Composable
fun PasswordField(password:String, onTextFieldChange: (String) -> Unit){

    OutlinedTextField(
        value = password,
        onValueChange = {onTextFieldChange(it)},
        label = {Text(text = "Enter your Password")},
        modifier = Modifier
            .fillMaxWidth(),
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
fun SignUpButton(isSignUpEnabled:Boolean,isLoading:Boolean,onClick: () -> Unit){
    Button(
        onClick = {
            Log.d("SignUpButton", "Botón SignUp presionado")
            onClick()},
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = primaryContainerLight,
            contentColor = outlineVariantLightMediumContrast
        )
    ) {
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.labelLarge
        )
    }

}

@Composable
fun LogInRedirection(){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Already a member?",
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.padding(start = 4.dp))
        Text(
            text = "Log In",
            style = MaterialTheme.typography.labelLarge,
            color = tertiaryLight
        )
    }
}




@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    MovieReservationSystemTheme {

    }
}