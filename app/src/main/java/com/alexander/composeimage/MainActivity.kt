package com.alexander.composeimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import com.alexander.composeimage.ui.theme.ComposeImageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeImageTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("")
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Greeting(name: String) {
    var X = remember { mutableStateListOf(0f) }
    var Y = remember { mutableStateListOf(0f) }
    var Fingers = remember { mutableStateOf (0) }
    val number = ImageBitmap.imageResource(id = R.drawable.number1)
    val numbers = arrayListOf(
        ImageBitmap.imageResource(id = R.drawable.number1),
        ImageBitmap.imageResource(id = R.drawable.number2),
        ImageBitmap.imageResource(id = R.drawable.number3),
        ImageBitmap.imageResource(id = R.drawable.number4),
        ImageBitmap.imageResource(id = R.drawable.number5)
    )
    Box(
    modifier = Modifier
    .fillMaxSize()
    .pointerInteropFilter { event ->
        Fingers.value = event.getPointerCount()
        X.clear()
        Y.clear()
        for (i in 0..Fingers.value - 1) {
            X.add( event.getX(i))
            Y.add (event.getY(i))
        }
        true
    }

){
        Canvas(modifier = Modifier) {
            for (i in 0..Fingers.value - 1) {
                //drawCircle(Color.Blue, 50f, Offset(X[i], Y[i]))
                //drawImage(number, Offset(p.x-number.width/2,p.y-number.height/2))
                drawImage(numbers[i % 5], Offset(X[i]-number.width/2, Y[i]-number.height/2))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeImageTheme {
        Greeting("")
    }
}