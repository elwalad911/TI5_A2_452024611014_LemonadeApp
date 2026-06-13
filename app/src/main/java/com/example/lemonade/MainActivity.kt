package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@Composable
fun LemonApp() {
    var currentStep by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf(0) }
    var squeezeRequired by remember { mutableIntStateOf(2) }
    val borderColor = Color(105, 205, 216)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {

            1 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = stringResource(R.string.lemon_select),
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(R.drawable.lemon_tree),
                        contentDescription = stringResource(R.string.lemon_tree_content_description),
                        modifier = Modifier
                            .wrapContentSize()
                            .border(
                                BorderStroke(2.dp, borderColor),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .clickable {
                                // Saat lemon dipetik, tentukan dulu berapa
                                // kali harus diperas (acak 2-4)
                                squeezeRequired = (2..4).random()
                                squeezeCount = 0  // reset hitungan
                                currentStep = 2
                            }
                    )
                }
            }

            2 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = stringResource(R.string.lemon_squeeze),
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(R.drawable.lemon_squeeze),
                        contentDescription = stringResource(R.string.lemon_content_description),
                        modifier = Modifier
                            .wrapContentSize()
                            .border(
                                BorderStroke(2.dp, borderColor),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .clickable {
                                squeezeCount++
                                if (squeezeCount >= squeezeRequired) {
                                    currentStep = 3
                                }
                            }
                    )
                }
            }

            3 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = stringResource(R.string.lemonade_drink),
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(R.drawable.lemon_drink),
                        contentDescription = stringResource(R.string.glass_of_lemonade_content_description),
                        modifier = Modifier
                            .wrapContentSize()
                            .border(
                                BorderStroke(2.dp, borderColor),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .clickable {
                                currentStep = 4
                            }
                    )
                }
            }

            4 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = stringResource(R.string.lemonade_empty_glass),
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(R.drawable.lemon_restart),
                        contentDescription = stringResource(R.string.empty_glass_content_description),
                        modifier = Modifier
                            .wrapContentSize()
                            .border(
                                BorderStroke(2.dp, borderColor),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .clickable {
                                currentStep = 1
                            }
                    )
                }
            }
        }
    }
}
