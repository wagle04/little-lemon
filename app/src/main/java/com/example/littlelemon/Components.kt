package com.example.littlelemon

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun MenuButton(label: String = "Label", onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .clip(RoundedCornerShape(10.dp))
            .background(LittleLemonColor.lightGrey)
            .padding(10.dp)
    ) {
        Text(
            text = label,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

//@Composable
//fun XTextFiled(label:String= "Label",placeHolderText:String="Placeholder Text"){
//    TextField(value = , onValueChange = )
//}

//
//@Composable
//fun XButton(
//    text: String = "Button",
//    textColor: Color = Color.Black,
//    backGroundColor: Color = secondaryColor,
//    borderRadius: Double = 16.0,
//    borderWidth: Double = 2.0,
//    borderColor: Color = Color.Black,
//) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(backGroundColor),
//        contentAlignment = Alignment.Center,
//    ) {
//        Text(
//            text = text,
//            color = textColor,
//            fontSize = 20.sp,
//            modifier = Modifier.padding(10.dp)
//        )
//    }
//}