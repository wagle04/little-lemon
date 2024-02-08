package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.navigation.Home
import com.example.littlelemon.ui.theme.primaryColor
import com.example.littlelemon.ui.theme.primaryColor2
import com.example.littlelemon.utils.*

@Composable
fun OnboardingScreen(navController: NavController) {

    var firstname by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var lastname by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var email by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var fieldErrors by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(SP_APPNAME, Context.MODE_PRIVATE)

    fun validateFields() {
        fieldErrors = false
        if (firstname.text.isEmpty()) {
            fieldErrors = true;
        }
        if (lastname.text.isEmpty()) {
            fieldErrors = true;

        }
        if (email.text.isEmpty()) {
            fieldErrors = true;

        }
        if (Validators.validateEmail(email.text)) {
            fieldErrors = true;

        }
    }


    Column {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(primaryColor),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Let's get to know you",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontWeight = FontWeight.W700,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 30.dp, horizontal = 10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {


            Column() {

                Text(
                    text = "Personal Information",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(bottom = 50.dp)
                )

                OutlinedTextField(
                    value = firstname,
                    onValueChange = {
                        firstname = it
                    },
                    maxLines = 1,
                    label = { Text(text = "First name", color = Color.Black) },
                    placeholder = { Text(text = "Enter your first name", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = primaryColor,
                        unfocusedBorderColor = Color.Gray
                    ),
                )

                Box(modifier = Modifier.padding(bottom = 20.dp))

                OutlinedTextField(
                    value = lastname,
                    onValueChange = {
                        lastname = it

                    }, maxLines = 1,

                    label = { Text(text = "Last name", color = Color.Black) },
                    placeholder = { Text(text = "Enter your last name", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = primaryColor,
                        unfocusedBorderColor = Color.Gray
                    )
                )

                Box(modifier = Modifier.padding(bottom = 20.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    maxLines = 1,
                    label = { Text(text = "Email", color = Color.Black) },
                    placeholder = { Text(text = "Enter your email", color = Color.Gray) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = primaryColor,
                        unfocusedBorderColor = Color.Gray
                    )
                )


            }

            Column {
                if (fieldErrors) Text(
                    text = "Registration unsuccessful. Please enter all data and enter valid email address only.",
                    fontSize = 12.sp,
                    color = Color.Red,
                )
                OutlinedButton(
                    onClick = {
                        validateFields()
                        println(fieldErrors)
                        if (!fieldErrors) {

                            //save to shared preferences
                            sharedPreferences.edit(commit = true) {
                                putString(SP_FIRSTNAME, firstname.text)
                                putString(SP_LASTNAME, lastname.text)
                                putString(SP_EMAIL, email.text)
                                putBoolean(SP_LOGGEDIN, true)
                            }

                            Toast.makeText(context, "Registration successful!", Toast.LENGTH_LONG)
                                .show()

                            //naviagte to home screen and clear previous navigation stack
                            navController.navigate(Home.route) {
                                popUpTo(0)
                            }
                        }
                    },
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(15),
                    colors =
                    ButtonDefaults.outlinedButtonColors(
                        backgroundColor = primaryColor2
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Register", color = Color.Black, fontWeight = FontWeight.SemiBold)
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    OnboardingScreen(rememberNavController())
}