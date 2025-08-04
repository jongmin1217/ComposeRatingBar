package com.bellmin.ComposeRatingBar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
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
                    SampleScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SampleScreen(
    modifier: Modifier
){
    var rating by remember { mutableFloatStateOf(3f) }
    var itemCount by remember { mutableIntStateOf(5) }
    var step by remember { mutableFloatStateOf(1f) }
    var selectedColor by remember { mutableStateOf(Color.Yellow) }
    var unselectedColor by remember { mutableStateOf(Color.LightGray) }
    var size by remember { mutableIntStateOf(20) }
    var painterId by remember { mutableIntStateOf(R.drawable.ic_star) }
    var itemSpacing by remember { mutableIntStateOf(4) }
    var enable by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
    ) {
        RatingBar(
            modifier = Modifier
                .padding(top = 40.dp)
                .align(Alignment.CenterHorizontally),
            rating = rating,
            onRatingChange = { rating = it },
            painter = painterResource(id = painterId),
            itemWidth = size.dp,
            itemHeight = size.dp,
            itemSpacing = itemSpacing.dp,
            selectedColor = selectedColor,
            unselectedColor = unselectedColor,
            itemCount = itemCount,
            step = step,
            enabled = enable
        )

        Text(
            text = rating.toString(),
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            RatingImage(
                modifier = Modifier
                    .fillMaxWidth(),
                painterId = painterId,
                selectedColor = selectedColor,
                unselectedColor = unselectedColor
            ){
                painterId = it
            }

            SettingColorContent(
                modifier = Modifier
                    .fillMaxWidth(),
                title = stringResource(R.string.selected_color),
                color = selectedColor
            ) {
                selectedColor = it
            }

            SettingColorContent(
                modifier = Modifier
                    .fillMaxWidth(),
                title = stringResource(R.string.unselected_color),
                color = unselectedColor
            ) {
                unselectedColor = it
            }

            SettingNumberContent(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.item_count),
                value = itemCount.toString(),
                isShowUp = itemCount < 10,
                isShowDown = itemCount > 1,
                onUpClick = {
                    itemCount += 1
                },
                onDownClick = {
                    itemCount -= 1
                }
            )

            SettingNumberContent(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.step),
                value = step.toString(),
                isShowUp = step < 1,
                isShowDown = step > 0.1,
                onUpClick = {
                    val value = step + 0.1f
                    step = value.toOneDecimal()
                },
                onDownClick = {
                    val value = step - 0.1f
                    step = value.toOneDecimal()
                }
            )

            SettingNumberContent(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.size),
                value = size.toString(),
                isShowUp = size < 30,
                isShowDown = size > 5,
                onUpClick = {
                    size += 1
                },
                onDownClick = {
                    size -= 1
                }
            )

            SettingNumberContent(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.item_spacing),
                value = itemSpacing.toString(),
                isShowUp = itemSpacing < 20,
                isShowDown = itemSpacing > 0,
                onUpClick = {
                    itemSpacing += 1
                },
                onDownClick = {
                    itemSpacing -= 1
                }
            )

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 30.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.enable),
                    style = TextStyle(
                        fontSize = 15.dp.textSp,
                        fontWeight = W700
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 20.dp)
                )

                Switch(
                    checked = enable,
                    onCheckedChange = {
                        enable = it
                    }
                )
            }
        }
    }
}

@Composable
fun RatingImage(
    modifier: Modifier = Modifier,
    painterId: Int,
    selectedColor: Color,
    unselectedColor: Color,
    onChangedPainterId: (Int) -> Unit
){
    val imageList = listOf(
        R.drawable.ic_star,
        R.drawable.ic_heart,
        R.drawable.ic_dog,
        R.drawable.ic_cat,
        R.drawable.ic_panda
    )

    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.rating_image),
            modifier = Modifier
                .padding(top = 30.dp)
                .align(Alignment.CenterHorizontally),
            style = TextStyle(
                fontSize = 20.dp.textSp,
                fontWeight = W700
            )
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .horizontalScroll(rememberScrollState())
        ){
            Spacer(modifier = Modifier.width(20.dp))
            imageList.forEach {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            color = if(painterId == it) selectedColor else unselectedColor
                        ).clickable{
                            onChangedPainterId(it)
                        }
                ){
                    Image(
                        painter = painterResource(it),
                        contentDescription = "",
                        modifier = Modifier.align(Center).size(80.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}

@Composable
fun SettingColorContent(
    modifier: Modifier = Modifier,
    title: String,
    color: Color,
    onChangeColor: (Color) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(top = 30.dp)
                .align(Alignment.CenterHorizontally),
            style = TextStyle(
                fontSize = 20.dp.textSp,
                fontWeight = W700
            )
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            val colorList = listOf(
                Color.Black,
                Color.DarkGray,
                Color.Gray,
                Color.LightGray,
                Color.White,
                Color.Red,
                Color.Green,
                Color.Blue,
                Color.Yellow,
                Color.Cyan,
                Color.Magenta
            )
            Spacer(modifier = Modifier.width(20.dp))
            colorList.forEach {
                val isSelect = it == color

                Box(
                    modifier = Modifier
                        .border(
                            width = 3.dp,
                            color = if (isSelect) {
                                if (it == Color.Red) Color.Black
                                else Color.Red
                            } else Color.Transparent
                        )
                        .clickable {
                            onChangeColor(it)
                        }
                ) {
                    Spacer(
                        modifier = Modifier
                            .size(40.dp)
                            .background(it)
                    )
                }   
            }
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}

@Composable
fun SettingNumberContent(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    isShowDown: Boolean,
    isShowUp: Boolean,
    onUpClick: () -> Unit,
    onDownClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(top = 30.dp)
                .align(Alignment.CenterHorizontally),
            style = TextStyle(
                fontSize = 20.dp.textSp,
                fontWeight = W700
            )
        )

        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            IconButton(
                modifier = Modifier
                    .size(40.dp)
                    .border(
                        width = 1.dp,
                        color = Color.DarkGray,
                        shape = CircleShape
                    )
                    .align(Alignment.CenterVertically),
                onClick = {
                    if(isShowDown) onDownClick()
                }
            ) {
                if(isShowDown) Icon(imageVector = Icons.Rounded.KeyboardArrowDown, contentDescription = "down")
            }

            Text(
                text = value,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterVertically)
            )

            IconButton(
                modifier = Modifier
                    .size(40.dp)
                    .border(
                        width = 1.dp,
                        color = Color.DarkGray,
                        shape = CircleShape
                    )
                    .align(Alignment.CenterVertically),
                onClick = {
                    if(isShowUp) onUpClick()
                }
            ) {
                if(isShowUp) Icon(imageVector = Icons.Rounded.KeyboardArrowUp, contentDescription = "up")
            }
        }
    }
}


val Dp.textSp: TextUnit
    @Composable get() = with(LocalDensity.current) {
        this@textSp.toSp()
    }

@SuppressLint("DefaultLocale")
fun Float.toOneDecimal(): Float = String.format("%.1f", this).toFloat()
