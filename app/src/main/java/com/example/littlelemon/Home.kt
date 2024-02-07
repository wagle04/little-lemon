package com.example.littlelemon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.littlelemon.navigation.Profile
import com.example.littlelemon.ui.theme.primaryColor2

@Composable
fun HomeScreen(navController: NavController) {
    Column {
        Text(text = "Home Screen")
        OutlinedButton(
            onClick = {
                //naviagte to profile screen
                navController.navigate(Profile.route)
            },
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(15),
            colors =
            ButtonDefaults.outlinedButtonColors(
                backgroundColor = primaryColor2
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Profile", color = Color.Black, fontWeight = FontWeight.SemiBold)
        }
    }

}