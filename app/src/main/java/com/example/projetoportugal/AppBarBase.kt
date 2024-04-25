package com.example.projetoportugal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projetoportugal.ui.theme.ProjetoPortugalTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier,
    title: String,
    nextTitle: String? = "",
    nextActive: Boolean? = false,
    onBackPressed: () -> Unit = {},
    onForwardPress: () -> Unit = {},
) {
    modifier
        .size(width = 70.dp, height = 50.dp)
    TopAppBar(
        modifier = modifier,
        title = {
            val customTextStyle = remember {
                TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp,
                    letterSpacing = (-0.01).sp
                )
            }

            Text(modifier = Modifier.fillMaxWidth(),
                text = title,
                style = customTextStyle,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                textAlign = TextAlign.Center)
        },
        navigationIcon = {
            Box(contentAlignment = Alignment.Center) {
                IconButton(content = {
                    Icon(
                        painterResource(id = R.drawable.ic_exit_to_app),
                        contentDescription = stringResource(id = R.string.title_activity_home),
                        tint = colorResource(id = R.color.secondary)
                    )
                }, onClick = onBackPressed)
            }
        },
        actions = {
            Box(contentAlignment = Alignment.Center, modifier = modifier
                .absolutePadding(right = 10.dp)) {
                IconButton(content = {
                    Text(text = nextTitle.orEmpty(), textAlign = TextAlign.Center,
                        color = colorResource(id = if (nextActive == true) R.color.primary else R.color.primary))
                }, onClick = { if (nextActive == true) onForwardPress() })
            }
        }
    )
}

@Preview
@Composable
private fun PreviewHaiiloAppBar() {
    ProjetoPortugalTheme {
       AppBar(modifier = Modifier.fillMaxWidth(), "App bar")
    }
}

@Preview
@Composable
private fun PreviewHaiiloAppBarWithActiveNext() {
    ProjetoPortugalTheme {
        AppBar(modifier = Modifier.fillMaxWidth(), "App bar", "Events", nextActive = true)
    }
}
