package com.example.moviereservationsystem.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviereservationsystem.R
import com.example.moviereservationsystem.ui.theme.inversePrimaryLight
import com.example.moviereservationsystem.ui.theme.tertiaryContainerLight


data class Genre(
    val name: String,
    val isSelected: Boolean = false
)


@Preview(showBackground = true)
@Composable
fun HomeScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        Home(modifier = Modifier.align(Alignment.Center))
    }
}


@Composable
fun Home(modifier: Modifier){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar()
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            GenresFilter()
        }
        Spacer(modifier = Modifier.width(8.dp))
        NavigationBar()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        TopAppBar(
            title ={ Text("Home") },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        painterResource(R.drawable.menu_icon),
                        contentDescription = "Menu_Icon"
                    )
                }
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        painterResource(R.drawable.user_icon),
                        contentDescription = "User_Icon"
                    )
                }
            }
        )
    }
}

@Composable
fun GenresFilter(){
    var genres by remember {
        mutableStateOf(
            listOf(
                Genre("Action"),
                Genre("Comedy"),
                Genre("Drama"),
                Genre("Horror"),
                Genre("Sci-Fi"),
                Genre("Thriller"),
                Genre("Romance"),
                Genre("Adventure"),
                Genre("Animation"),
                Genre("Crime"),
                Genre("Mystery"),
            )
        )
    }

    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        userScrollEnabled = true
    ){
        items(genres.size){ index ->
            val genre = genres[index]
            FilterChip(
                selected = genre.isSelected,
                onClick = {
                    genres = genres.mapIndexed { i, g ->
                        if (i == index) g.copy(isSelected = !g.isSelected) else g
                    }
                },
                label = { Text(genre.name) },
                colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = tertiaryContainerLight
                ),
                leadingIcon = if (genre.isSelected) {
                    {
                        Icon(
                            painterResource(R.drawable.check_icon),
                            contentDescription = "Check_Icon"
                        )
                    }
                } else {
                    null
                }
            )
            
        }
    }
}

@Composable
fun NavigationBar(){

    var selected by remember { mutableStateOf(false) }

    BottomAppBar(
    ) {
        NavigationBarItem(
            selected = selected,
            onClick = { selected = !selected },
            icon = {
                Icon(
                    painterResource(R.drawable.home_icon),
                    contentDescription = "Home_Icon",
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = inversePrimaryLight,
                selectedTextColor = inversePrimaryLight,
            )
        )
        Spacer(Modifier.width(8.dp))
        NavigationBarItem(
            selected = selected,
            onClick = { selected = !selected },
            icon = {
                Icon(
                    painterResource(R.drawable.booking_icon),
                    contentDescription = "Booking_Icon"
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = inversePrimaryLight,
                selectedTextColor = inversePrimaryLight,
            )
        )
        Spacer(Modifier.width(8.dp))
        NavigationBarItem(
            selected = selected,
            onClick = { selected = !selected },
            icon = {
                Icon(
                    painterResource(R.drawable.history_icon),
                    contentDescription = "History_Icon"
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = inversePrimaryLight,
                selectedTextColor = inversePrimaryLight,
            )
        )
    }
}

