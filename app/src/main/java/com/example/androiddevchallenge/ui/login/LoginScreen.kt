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
package com.example.androiddevchallenge.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.utils.underline
import com.example.androiddevchallenge.ui.widget.PrimaryButton

@Composable
fun LoginScreen(goToHome: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(
                text = "Log in with email",
                modifier = Modifier
                    .wrapContentWidth()
                    .requiredHeight(200.dp)
                    .paddingFromBaseline(top = 184.dp)
                    .align(Alignment.CenterHorizontally),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h1,
            )
            val (email, onEmailChange) = remember { mutableStateOf("") }
            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(56.dp)
                    .padding(horizontal = 16.dp),
                textStyle = MaterialTheme.typography.body1,
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = MaterialTheme.colors.secondary,
                    focusedBorderColor =
                    MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high),
                ),
                placeholder = {
                    Text(
                        text = "Email address",
                        style = MaterialTheme.typography.body1
                    )
                }
            )
            Spacer(modifier = Modifier.requiredHeight(8.dp))
            val (password, onPasswordChange) = remember { mutableStateOf("") }
            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(56.dp)
                    .padding(horizontal = 16.dp),
                textStyle = MaterialTheme.typography.body1,
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = MaterialTheme.colors.secondary,
                    focusedBorderColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high),
                ),
                visualTransformation = PasswordVisualTransformation(),
                placeholder = {
                    Text(
                        text = "Password (8+ characters)",
                        style = MaterialTheme.typography.body1
                    )
                }
            )
            val description = buildAnnotatedString {
                append("By clicking below, you agree to our ")
                underline {
                    append("Terms of Use")
                }
                append(" and consent to our ")
                underline {
                    append("Privacy Policy")
                }
                append('.')
            }
            Text(
                text = description,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(56.dp)
                    .paddingFromBaseline(top = 24.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.body2,
            )
            PrimaryButton(
                text = "Log in",
                onClick = goToHome,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(48.dp)
                    .padding(horizontal = 16.dp),
            )
        }
    }
}
