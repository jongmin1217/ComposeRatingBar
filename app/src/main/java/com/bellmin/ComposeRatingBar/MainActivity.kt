package com.bellmin.ComposeRatingBar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bellmin.ComposeRatingBar.ui.theme.ComposeRatingBarTheme
import com.bellmin.ratingbar.RatingBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeRatingBarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var rating by remember { mutableFloatStateOf(3.5f) }
                    val painter = painterResource(id = R.drawable.ic_launcher_foreground)

                    Column(
                        modifier = Modifier.padding(innerPadding).fillMaxSize()
                    ) {
                        RatingBar(
                            modifier = Modifier.padding(top = 100.dp),
                            rating = rating,
                            onRatingChange = { rating = it },
                            painter = painter,
                            itemWidth = 40.dp,
                            itemHeight = 40.dp,
                            selectedColor = Color.Red,
                            unselectedColor = Color.LightGray,
                            itemCount = 5,
                            step = 0.1f
                        )

                        Text(
                            text = rating.toString(),
                            modifier = Modifier.padding(top = 30.dp)
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeRatingBarTheme {
        Greeting("Android")
    }
}