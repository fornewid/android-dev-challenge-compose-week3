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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.LocalAbsoluteElevation
import androidx.compose.material.LocalElevationOverlay
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun HomeContents(modifier: Modifier = Modifier) {
    val (query, onQueryChange) = remember { mutableStateOf("") }
    LazyColumn(modifier = modifier) {
        item {
            Spacer(modifier = Modifier.requiredHeight(40.dp))
        }
        item {
            OutlinedTextField(
                value = query,
                onValueChange = onQueryChange,
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
                    Row(modifier = Modifier.wrapContentHeight()) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier.requiredSize(size = 18.dp)
                        )
                        Text(
                            text = "Search",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .padding(start = 6.dp)
                                .offset(y = (-2).dp)
                        )
                    }
                }
            )
        }
        item {
            Text(
                text = "Browse themes",
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(48.dp)
                    .paddingFromBaseline(top = 32.dp)
                    .padding(horizontal = 16.dp),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h1,
            )
        }
        item {
            HomeThemeList(items = Theme.defaults())
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Design your home garden",
                    modifier = Modifier
                        .weight(1f)
                        .requiredHeight(48.dp)
                        .paddingFromBaseline(top = 32.dp),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.h1,
                )
                Icon(
                    Icons.Default.FilterList,
                    contentDescription = "filter",
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(bottom = 10.dp)
                )
            }
        }
        Plant.defaults().forEach { theme ->
            item {
                HomePlantItem(plant = theme)
            }
            item {
                Spacer(modifier = Modifier.requiredHeight(8.dp))
            }
        }
    }
}

@Composable
private fun HomeThemeList(items: List<Theme>) {
    LazyRow(
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 0.dp,
            bottom = 8.dp
        )
    ) {
        items.forEachIndexed { index, theme ->
            if (index > 0) {
                item {
                    Spacer(modifier = Modifier.requiredWidth(8.dp))
                }
            }
            item {
                HomeThemeItem(theme = theme)
            }
        }
    }
}

@Composable
private fun HomeThemeItem(
    theme: Theme,
    modifier: Modifier = Modifier
) {
    val elevation = 1.dp
    val elevationOverlay = LocalElevationOverlay.current
    val absoluteElevation = LocalAbsoluteElevation.current + elevation
    val color = MaterialTheme.colors.background
    val backgroundColor = elevationOverlay?.apply(color, absoluteElevation) ?: color
    Card(
        shape = MaterialTheme.shapes.small,
        backgroundColor = backgroundColor,
        modifier = modifier.requiredSize(width = 136.dp, height = 136.dp)
    ) {
        Column {
            GlideImage(
                data = theme.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(17 / 12f)
            )
            Text(
                text = theme.name,
                modifier = Modifier
                    .fillMaxSize()
                    .paddingFromBaseline(bottom = 16.dp)
                    .padding(horizontal = 16.dp),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h2
            )
        }
    }
}

@Composable
private fun HomePlantItem(plant: Plant) {
    val (checked, onCheckedChange) = remember { mutableStateOf(plant.initChecked) }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(64.dp)
            .clickable { onCheckedChange(checked.not()) }
    ) {
        val (image, title, description, checkbox, divider) = createRefs()
        GlideImage(
            data = plant.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(image) {
                    height = Dimension.fillToConstraints
                    linkTo(top = parent.top, bottom = parent.bottom)
                    start.linkTo(parent.start, 16.dp)
                }
                .aspectRatio(1f)
                .clip(shape = RoundedCornerShape(4.dp)),
        )
        Text(
            text = plant.name,
            modifier = Modifier
                .constrainAs(title) {
                    width = Dimension.fillToConstraints
                    start.linkTo(image.end, 16.dp)
                    top.linkTo(image.top)
                    end.linkTo(checkbox.start, 8.dp)
                }
                .paddingFromBaseline(top = 24.dp),
            style = MaterialTheme.typography.h2
        )
        Text(
            modifier = Modifier
                .constrainAs(description) {
                    width = Dimension.fillToConstraints
                    start.linkTo(image.end, 16.dp)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(checkbox.start, 8.dp)
                }
                .paddingFromBaseline(bottom = 24.dp),
            text = "This is a description",
            style = MaterialTheme.typography.body1
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkmarkColor = MaterialTheme.colors.background
            ),
            modifier = Modifier.constrainAs(checkbox) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end, 16.dp)
            },
        )
        Divider(
            modifier = Modifier.constrainAs(divider) {
                width = Dimension.fillToConstraints
                bottom.linkTo(parent.bottom)
                start.linkTo(image.end, 8.dp)
                end.linkTo(parent.end, 16.dp)
            }
        )
    }
}

private data class Theme(
    val imageUrl: String,
    val name: String
) {
    companion object {
        fun defaults(): List<Theme> = listOf(
            Theme(
                name = "Desert chic",
                imageUrl = "https://images.pexels.com/photos/2132227/pexels-photo-2132227.jpeg?auto=compress&cs=tinysrgb&h=200"
            ),
            Theme(
                name = "Tiny terrariums",
                imageUrl = "https://images.pexels.com/photos/1400375/pexels-photo-1400375.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=200"
            ),
            Theme(
                name = "Jungle vibes",
                imageUrl = "https://images.pexels.com/photos/5699665/pexels-photo-5699665.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=200"
            ),
            Theme(
                name = "Easy care",
                imageUrl = "https://images.pexels.com/photos/6208086/pexels-photo-6208086.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=200"
            ),
            Theme(
                name = "Statements",
                imageUrl = "https://images.pexels.com/photos/3511755/pexels-photo-3511755.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=200"
            )
        )
    }
}

private data class Plant(
    val imageUrl: String,
    val name: String,
    val initChecked: Boolean = false
) {
    companion object {
        fun defaults(): List<Plant> = listOf(
            Plant(
                name = "Aglaonema",
                imageUrl = "https://images.pexels.com/photos/4751978/pexels-photo-4751978.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=100",
                initChecked = true
            ),
            Plant(
                name = "Peace lily",
                imageUrl = "https://images.pexels.com/photos/4425201/pexels-photo-4425201.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=100"
            ),
            Plant(
                name = "Fiddle leaf",
                imageUrl = "https://images.pexels.com/photos/6208087/pexels-photo-6208087.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=100"
            ),
            Plant(
                name = "Snake plant",
                imageUrl = "https://images.pexels.com/photos/2123482/pexels-photo-2123482.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=100"
            ),
            Plant(
                name = "Pothos",
                imageUrl = "https://images.pexels.com/photos/1084199/pexels-photo-1084199.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=100"
            ),
        )
    }
}
