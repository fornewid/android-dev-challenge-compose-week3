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
package com.example.androiddevchallenge.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.insets.navigationBarsHeight
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun HomeScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val tabs = HomeTabs.values()
        Scaffold(
            bottomBar = {
                BottomNavigation(
                    modifier = Modifier.navigationBarsHeight(additional = 56.dp),
                    backgroundColor = MaterialTheme.colors.primary,
                    elevation = 16.dp
                ) {
                    tabs.forEach { tab ->
                        BottomNavigationItem(
                            icon = { Icon(tab.icon, contentDescription = tab.title) },
                            label = { Text(tab.title) },
                            selected = tab == HomeTabs.Home,
                            onClick = {},
                            selectedContentColor = MaterialTheme.colors.onBackground,
                            modifier = Modifier.navigationBarsPadding()
                        )
                    }
                }
            }
        ) { innerPadding ->
            HomeContents(modifier = Modifier.padding(innerPadding))
        }
    }
}

private enum class HomeTabs(
    val title: String,
    val icon: ImageVector
) {
    Home("Home", Icons.Default.Home),
    Favorites("Favorites", Icons.Default.FavoriteBorder),
    Profile("Profile", Icons.Default.AccountCircle),
    Cart("Cart", Icons.Default.ShoppingCart)
}
