# RatingBar

RatingBar is a highly customizable and stateless rating bar component for Jetpack Compose on Android.
It allows users to select fractional ratings with custom step intervals, supports custom icons and spacing, and provides full control over appearance and interaction.

<img src="https://github.com/jongmin1217/ComposeRatingBar/blob/main/readme/sample.gif"></img>
  
    
### Features
- Fractional Step Support: Set ratings in steps (e.g. 0.1, 0.5, 1.0, etc.)
- Custom Item Count: Easily configure how many rating items are shown.
- Custom Icons: Pass your own icon with a Painter, control icon size and spacing.
- Custom Colors: Animate between selected/unselected colors.
- Stateless: No internal state—easy integration with your own state management.
- Enable/Disable: Optionally block user interaction.
- Precise Touch Handling: Updates rating accurately on tap and drag.
- Half/Partial Fill: Icons show filled, half-filled, and empty states according to rating.


### Installation
To use StepSlider in your Android project, follow these steps:

1. Add the JitPack repository to your root build.gradle at the end of repositories:

```kotlin
repositories {
    maven("https://jitpack.io")
}
```
2. Add the dependency in your app-level build.gradle:
```kotlin
dependencies {
    implementation("com.github.jongmin1217:ComposeRatingBar:<latest_version>")
}
```


### Usage
Add the RatingBar composable to your UI layout and customize its appearance and behavior as needed:

```kotlin
@Composable
fun MyScreen() {
    var rating by remember { mutableStateOf(3.5f) }

    RatingBar(
        modifier = Modifier,
        painter = painterResource(R.drawable.ic_star),
        rating = rating,
        onRatingChange = { newValue ->
            rating = newValue
        }
    )
}
```
For advanced customization, you can modify the 'itemCount', 'colr', and other properties of the StepSlider.

```kotlin
RatingBar(
    modifier = Modifier,
    rating = rating,
    onRatingChange = { rating = it },
    step = 0.1f,
    itemCount = 7,
    painter = painterResource(R.drawable.ic_star),
    itemWidth = 32.dp,
    itemHeight = 32.dp,
    itemSpacing = 8.dp,
    selectedColor = Color.Red,
    unselectedColor = Color.Gray,
    enabled = true,
)
```
## Documentation
### RatingBar
|Parameter|Data type|Description|default|
|:---|:---|:---|:---|
|rating|Float|Current rating value||
|onRatingChange|(Float) -> Unit|Called when the user changes the rating||
|painter|Painter|The icon to use for each rating item||
|step|Float|Minimum step for rating changes|0.5f|
|itemCount|Int|Number of rating items|5|
|enabled|Boolean|Enables/disables user touch interaction|true|
|itemWidth|Dp|Width of each rating icon|24.dp|
|itemHeight|Dp|Height of each rating icon|24.dp|
|itemSpacing|Dp|Space between each icon|4.dp|
|selectedColor|Color|Color for selected/filled icons|Color.Yellow|
|unselectedColor|Color|Color for unselected/empty icons|Color.Gray|


# License
ComposeRatingBar is distributed under the terms of the Apache License (Version 2.0). See the [license](https://github.com/jongmin1217/ComposeRatingBar/blob/main/LICENSE) for more information.
