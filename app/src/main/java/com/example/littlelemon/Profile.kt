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
import com.example.littlelemon.navigation.Onboarding
import com.example.littlelemon.ui.theme.primaryColor
import com.example.littlelemon.ui.theme.primaryColor2
import com.example.littlelemon.utils.*

@Composable
fun ProfileScreen(navController: NavController) {

    var firstname by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var lastname by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var email by remember {
        mutableStateOf(TextFieldValue(""))
    }


    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(SP_APPNAME, Context.MODE_PRIVATE)

    firstname = TextFieldValue(text = sharedPreferences.getString(SP_FIRSTNAME, "--")!!)
    lastname = TextFieldValue(text = sharedPreferences.getString(SP_LASTNAME, "--")!!)
    email = TextFieldValue(text = sharedPreferences.getString(SP_EMAIL, "--")!!)

    Column {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )
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
                    onValueChange = {},
                    maxLines = 1,
                    enabled = false,
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
                    onValueChange = {},
                    maxLines = 1,
                    enabled = false,
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
                    onValueChange = {},
                    maxLines = 1,
                    enabled = false,
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

            OutlinedButton(
                onClick = {
                    //remove from shared preferences
                    sharedPreferences.edit()
                        .remove(SP_FIRSTNAME)
                        .remove(SP_LASTNAME)
                        .remove(SP_EMAIL)
                        .commit()

                    sharedPreferences.edit(commit = true) {
                        putBoolean(SP_LOGGEDIN, false)
                    }

                    Toast.makeText(context, "Logged Out!", Toast.LENGTH_LONG)
                        .show()

                    //naviagte to onboarding screen and clear previous navigation stack
                    navController.navigate(Onboarding.route) {
                        popUpTo(0)
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
                Text(text = "Log Out", color = Color.Black, fontWeight = FontWeight.SemiBold)

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(rememberNavController())
}