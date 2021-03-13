/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.welcome

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.widget.PrimaryButton
import com.example.androiddevchallenge.ui.widget.SecondaryButton

@Composable
fun WelcomeScreen(goToLogin: () -> Unit) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxSize()
    ) {
        Box {
            Image(
                painter = painterResource(R.drawable.welcome_bg),
                contentDescription = null,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
            Column {
                Spacer(modifier = Modifier.requiredHeight(72.dp))
                Image(
                    painter = painterResource(R.drawable.welcome_illos),
                    contentDescription = null,
                    modifier = Modifier
                        .requiredSize(width = 310.dp, height = 280.dp)
                        .offset(x = 88.dp)
                )
                Spacer(modifier = Modifier.requiredHeight(48.dp))
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Beautiful home garden solutions",
                    modifier = Modifier
                        .wrapContentWidth()
                        .requiredHeight(72.dp)
                        .paddingFromBaseline(top = 32.dp)
                        .align(Alignment.CenterHorizontally),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.subtitle1,
                )
                PrimaryButton(
                    text = "Create account",
                    onClick = goToLogin,
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(48.dp)
                        .padding(horizontal = 16.dp),
                )
                Spacer(modifier = Modifier.requiredHeight(8.dp))
                SecondaryButton(
                    text = "Login in",
                    onClick = goToLogin,
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(48.dp)
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        WelcomeScreen(goToLogin = {})
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        WelcomeScreen(goToLogin = {})
    }
}
