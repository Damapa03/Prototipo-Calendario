package org.example.project.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator

class LoginScreen:Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var visibility by remember { mutableStateOf(false) }

        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            PedirLogin(username, password, visibility,
                onChangeValue = { user, pass ->
                    username = user
                    password = pass
                },
                onClickIcon = {visibility = !visibility}
                )

            Spacer(modifier = Modifier.height(24.dp))

            if (navigator != null) {
                Botones(username, password, navigator)
            }

        }
    }

    @Composable
    fun PedirLogin(
        username:String, 
        password:String, 
        visibility:Boolean,
        onChangeValue:(String, String) -> Unit, 
        onClickIcon:() -> Unit
    ) {
        // TextField para el username
        OutlinedTextField(
            value = username,
            onValueChange = { onChangeValue(it, password) },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp)) // Separador entre ambos campos
        // TextField para la password
        OutlinedTextField(
            value = password,
            onValueChange = { onChangeValue(username, it) },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = if(!visibility) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(onClick = onClickIcon) {
                    Icon(
                        imageVector = if (visibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "Visibility"
                    )
                }
            }
        )
    }

    @Composable
    fun Botones(
        username: String,
        password: String,
        navigator: Navigator
    ) {
        // Fila para poner los dos botones
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                if (username.isNotBlank() && password.isNotBlank()) {
                    navigator.push(CalendarScreen())
                }
            }) {
                Text("Iniciar sesión")
            }
        }
    }


}