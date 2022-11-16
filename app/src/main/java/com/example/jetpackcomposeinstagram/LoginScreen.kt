package com.example.jetpackcomposeinstagram

import android.app.Activity
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center))
        Footer(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Close App",
        modifier = modifier.clickable { activity.finish() }
    )
}

@Composable
fun Body(modifier: Modifier) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable{ mutableStateOf("") }
    var isBtnLoginEnable by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier) {
        Logo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        Email( email) {
            email = it
            isBtnLoginEnable = enableLogin(email,password)
        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(password) {
            password = it
            isBtnLoginEnable = enableLogin(email,password)
        }
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        BtnLogin(isBtnLoginEnable)
        Spacer(modifier = Modifier.size(8.dp))
        CustomDivider()
        Spacer(modifier = Modifier.size(24.dp))
        TextLoginFacebook()
    }
}

@Composable
fun Logo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "Logo", modifier = modifier
    )
}

@Composable
fun Email(email: String, onTextChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder =  { Text(text = "Phone number, username or email", color = Color(0XFFB2B2B2))},
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0XFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun Password(password: String, onTextChange: (String) -> Unit) {

    var isPasswordVisibility by rememberSaveable { mutableStateOf(false) }
    TextField(
        value = password, onValueChange = { onTextChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Password", color = Color(0XFFB2B2B2))},
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0XFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            var image = if (isPasswordVisibility){
                Icons.Filled.VisibilityOff
            }else{
                Icons.Filled.Visibility
            }
            
            IconButton(onClick = { isPasswordVisibility = !isPasswordVisibility}) {
                Icon(imageVector = image,
                    contentDescription = "Show password")
            }
        },
        visualTransformation = if (isPasswordVisibility){
            VisualTransformation.None
        }else{
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Forgot password?",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9),
        modifier = modifier
    )
}

@Composable
fun BtnLogin(loginEnable: Boolean) {

    Button(
        onClick = { /*TODO*/ },
        enabled = loginEnable, modifier = Modifier.fillMaxWidth(),
        colors =  ButtonDefaults.buttonColors(
            backgroundColor = Color(0XFF4EA8E9),
            disabledBackgroundColor = Color(0XFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Log In")
    }
}

@Composable
fun CustomDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(modifier = Modifier
            .weight(1f)
            .height(1.dp)
            .background(Color(0XFFF9F9F9)))

        Text(text = "OR", modifier = Modifier.padding(horizontal = 6.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0XFFB5B5B5)
        )

        Divider(modifier = Modifier
            .weight(1f)
            .height(1.dp)
            .background(Color(0XFFF9F9F9))
        )
    }
}

@Composable
fun TextLoginFacebook() {

    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Image(painter = painterResource(id = R.drawable.fb),
            contentDescription = "Login Facebook",
            modifier = Modifier.size(20.dp))

        Text(
            text = "Continue as Cristian Arboleda",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color(0xFF4EA8E9),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Composable
fun Footer(modifier: Modifier) {
    
    Column(modifier = modifier.fillMaxWidth()) {
        FooterDivider()
        Spacer(modifier = Modifier.size(32.dp))
        SingUp()
    }
}

@Composable
fun FooterDivider() {
    Divider(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0XFFF9F9F9)))
}

@Composable
fun SingUp() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
        , horizontalArrangement = Arrangement.Center) {

        Text(
            text = "Don't have an account?",
            fontSize = 12.sp,
            color = Color(0XFFB5B5B5)
        )

        Text(
            text = "Sing Up.",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF4EA8E9),
            modifier = Modifier.padding(horizontal = 6.dp)
        )
    }
}

// functions no composable
fun enableLogin(email: String, password: String):Boolean =
    Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6



