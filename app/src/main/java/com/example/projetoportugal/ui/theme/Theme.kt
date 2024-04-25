package com.example.projetoportugal.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import coil.compose.AsyncImage
import com.example.projetoportugal.R

private val DarkColorScheme = darkColorScheme(
    primary = com.example.projetoportugal.home.ui.theme.Purple80,
    secondary = com.example.projetoportugal.home.ui.theme.PurpleGrey80,
    tertiary = com.example.projetoportugal.home.ui.theme.Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = com.example.projetoportugal.home.ui.theme.Purple40,
    secondary = com.example.projetoportugal.home.ui.theme.PurpleGrey40,
    tertiary = com.example.projetoportugal.home.ui.theme.Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjetoPortugalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    hasToolbar: Boolean = false,
    hasStartButton: Triple<Int, String, () -> Unit> = Triple(0, "", {}),
    hasEndButton: Triple<Int, String, () -> Unit> = Triple(0, "", {}),
    title: String? = null,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = com.example.projetoportugal.home.ui.theme.Typography,
    ) {
        if (hasToolbar) {
            Column {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,

                            ) {

                            if ((hasStartButton.first == 0).not()) {
                                Icon(
                                    modifier = Modifier
                                        .weight(0.4f)
                                        .size(40.dp)
                                        .padding(end = 15.dp)
                                        .clickable {
                                            hasStartButton.third.invoke()
                                            //profileClick.invoke()
                                        },
                                    painter = painterResource(id = hasStartButton.first),
                                    contentDescription = "Image",
                                    tint = Color.Black
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .weight(0.4f)
                                        .size(40.dp)
                                        .padding(end = 15.dp)
                                ) {}
                            }

                            if (title.isNullOrBlank().not()) {
                                Text(
                                    text = title.toString(),
                                    modifier = Modifier
                                        .weight(1.2f)
                                        .padding(end = 20.dp),
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                            }

                            if ((hasEndButton.first == 0).not()) {
                                Box(
                                    modifier = Modifier
                                        .weight(0.3f)
                                        //.padding(end = 15.dp)
                                ) {
                                    AsyncImage(
                                        model = hasEndButton.second,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(50.dp)
                                            .height(50.dp)
                                            .clip(CircleShape)
                                            .clickable {
                                                hasEndButton.third.invoke()
                                            },
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            } else {
                                Box(
                                    modifier = Modifier
                                        .weight(0.4f)
                                        .size(40.dp)
                                        .padding(end = 15.dp)
                                ) {}
                            }
                        }

                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colorResource(
                            id = R.color.primary
                        )
                    )
                )
                content.invoke()
            }
        } else {
            content.invoke()
        }
    }
}