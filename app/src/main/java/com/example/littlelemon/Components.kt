package com.example.littlelemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.secondaryColor

//@Composable
//fun XTextFiled(label:String= "Label",placeHolderText:String="Placeholder Text"){
//    TextField(value = , onValueChange = )
//}


@Composable
fun XButton(
    text: String = "Button",
    textColor: Color = Color.Black,
    backGroundColor: Color = secondaryColor,
    borderRadius: Double = 16.0,
    borderWidth: Double = 2.0,
    borderColor: Color = Color.Black,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backGroundColor),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
    }
}