package com.example.moviereservationsystem.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviereservationsystem.ui.theme.MovieReservationSystemTheme
import com.example.moviereservationsystem.ui.theme.onPrimaryContainerLight
import com.example.moviereservationsystem.ui.theme.outlineVariantLightMediumContrast
import com.example.moviereservationsystem.ui.theme.primaryContainerLight
import com.example.moviereservationsystem.ui.theme.primaryLight
import com.example.moviereservationsystem.ui.theme.tertiaryLight

@Composable
fun SignUpScreen(){
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        SignUp(Modifier.align(Alignment.Center))
    }
}

@Composable
fun SignUp(modifier: Modifier){
    Column (modifier = modifier){
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.titleLarge,
            color = primaryLight,
            modifier = modifier
        )
        Spacer(modifier = Modifier.padding(16.dp))
        NameField()
        Spacer(modifier = Modifier.padding(16.dp))
        MobileNumberField()
        Spacer(modifier = Modifier.padding(16.dp))
        EmailField()
        Spacer(modifier = Modifier.padding(16.dp))
        PasswordField()
        Spacer(modifier = Modifier.padding(16.dp))
        SignUpButton {  }
        Spacer(modifier = Modifier.padding(8.dp))
        LogInRedirection()
    }

}


@Composable
fun NameField(){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {text = it},
        label = {Text(text = "Enter your Name")},
        modifier = Modifier.size(
            width = 272.dp,
            height = 40.dp
        ),
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
fun MobileNumberField(){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {text = it},
        label = {Text(text = "Enter your Number")},
        modifier = Modifier.size(
            width = 272.dp,
            height = 40.dp
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        singleLine = true,
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = onPrimaryContainerLight,
            focusedLabelColor = onPrimaryContainerLight

        )
    )
}

@Composable
fun EmailField(){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {text = it},
        label = {Text(text = "Enter your Email")},
        modifier = Modifier.size(
            width = 272.dp,
            height = 40.dp
        ),
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
fun PasswordField(){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {text = it},
        label = {Text(text = "Enter your Password")},
        modifier = Modifier.size(
            width = 272.dp,
            height = 40.dp
        ),
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
fun SignUpButton(onClick: () -> Unit){
    Button(
        onClick = {onClick()},
        modifier = Modifier.size(
            width = 272.dp,
            height = 40.dp
        ),
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
        SignUpScreen()
    }
}