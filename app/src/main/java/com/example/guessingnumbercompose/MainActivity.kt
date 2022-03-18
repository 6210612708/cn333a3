package com.example.guessingnumbercompose

import android.graphics.Color.rgb
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.example.guessingnumbercompose.ui.theme.GuessingNumberComposeTheme
import com.example.guessingnumbercompose.ui.theme.Purple500
import com.example.guessingnumbercompose.ui.theme.grey
import kotlin.random.Random.Default.nextInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessingNumber()
        }
    }
}


var random : Int = nextInt(1,1000)
var count: Int = 0
var cont: Boolean = true

@Composable
fun GuessingNumber() {

    val textState = remember { mutableStateOf(TextFieldValue()) }
    var answer by rememberSaveable { mutableStateOf("") }
    var button by rememberSaveable { mutableStateOf("Submit") }

//    Text(text = "$random")

    val checkAnswer =  {
        if (cont) {
            if (textState.value.text.isNotEmpty()) {
//                text.setTextColor(android.graphics.Color.rgb(51,212,255))

                val number: Int = textState.value.text.toString().toInt()
                count++

                if (number < random) {

                    answer = "wrong, your number is to low!"
                    button = "Submit"

                } else if (number > random) {

                    answer = "wrong, your number is to high!"
                    button = "Submit"

                } else {
                    answer = "Congratulation, your number is right! ,Your guessed is " + count + " times"
                    button = "Play again"
                    cont = false
                }
            } else {
                answer = "Please fill your answer"
            }
        } else {
            answer = ""
            button = "Submit"
            random = nextInt( 1, 1000)
            count = 0
            cont = true
        }

    }

//    var text by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Text(
            text = "Try to guess the number I'm thinking of from 1 - 1000",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Please enter your guess:",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
        )

        TextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
            },
            label = { Text(text = "Your Number") },
            placeholder = { Text(text = "Enter your number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = grey,
            focusedIndicatorColor = Color.Transparent, //hide the indicator
            unfocusedIndicatorColor = White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
//        Text("Your answer is: " + textState.value.text)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        )
        {
            Button(
                onClick = {
                    checkAnswer()
                }
            ) {
                Text(text = "$button", fontSize = 20.sp)
            }
        }
        Text(
            text = " $answer ",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)

        )
    }
}

