package com.example.anchor.ui.pages.Onboarding

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.anchor.ui.theme.AnchorTheme
@Composable
fun LoginScreen(){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Box(modifier = Modifier.height(300.dp).width(200.dp)) {
            Column() {
                Text(text = "Login Screen")
                Row() {
                    Text(text = "Name")

                }
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun loginScreenPreview(){
    AnchorTheme(darkTheme = true) {
        LoginScreen()
    }
}