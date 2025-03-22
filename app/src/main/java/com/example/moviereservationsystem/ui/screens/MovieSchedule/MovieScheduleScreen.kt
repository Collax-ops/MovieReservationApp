package com.example.moviereservationsystem.ui.screens.MovieSchedule

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.moviereservationsystem.R
import com.example.moviereservationsystem.ui.screens.theme.onSurfaceVariantLight
import com.example.moviereservationsystem.ui.screens.theme.tertiaryContainerLight

data class FakeDate(
    val month: String,
    val date: String,
    val dayofWeek: String,
    val isToday: Boolean,
    val isSelected: Boolean = false
)

data class FakeTheather(
    val name: String,
    val schedules: List<String>,
    val isSelected: Boolean = false
)


@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieScheduleScreen(
    movieId: Int,
    posterPath:String,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedContentScope,
){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        MovieSchedule(modifier = Modifier.align(Alignment.Center),sharedTransitionScope, animatedVisibilityScope,movieId,posterPath)
    }
}

@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieSchedule(
    modifier: Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedContentScope,
    movieId: Int,
    posterPath: String
){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar()
        Spacer(modifier = Modifier.width(8.dp))
        with(sharedTransitionScope) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w500${posterPath}")
                    .crossfade(true)
                    .placeholderMemoryCacheKey("$movieId")
                    .memoryCacheKey("$movieId")
                    .build(),
                contentDescription = "Movie Poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(300.dp, 440.dp)
                    .sharedBounds(
                        rememberSharedContentState(
                            key = "$movieId"
                        ),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
            )
        }
        DateItem()
        Spacer(modifier = Modifier.width(4.dp))
        TheatherSchedule()
    }
}

@Preview(showBackground = true)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(){
    Row (
        verticalAlignment = Alignment.CenterVertically,
    ){
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painterResource(R.drawable.movie_icon),
                        contentDescription = "movie_icon"
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        "Movie \n" + "Schedule"
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        painterResource(R.drawable.arrow_back_icon),
                        contentDescription = "arrow_back_icon"
                    )
                };
            }
        )
    }
}



@Preview(showBackground = true)
@Composable
fun DateItem(){
    var fakedates by remember {
        mutableStateOf(
            listOf(
            FakeDate(
                "Feb. ",
                "16",
                "Today",
                true
            ),
                FakeDate(
                    "Feb. ",
                    "17",
                    "Today",
                    true
            ), FakeDate(
                    "Feb. ",
                    "18",
                    "Today",
                    true
            ), FakeDate(
                    "Feb. ",
                    "19",
                    "Today",
                    true
            )
        )
        )
    }

    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        userScrollEnabled = true
    ){
        items(fakedates.size){ index ->
            val fakedate = fakedates[index]
            FilterChip(
                selected = fakedate.isSelected,
                onClick = {
                    fakedates = fakedates.mapIndexed { i, g ->
                        if (i == index) g.copy(isSelected = !g.isSelected) else g
                    }
                },
                label = { Text(
                    " ${fakedate.month} \n" +
                            " ${fakedate.date} \n " +
                            "${fakedate.dayofWeek}",
                    textAlign = TextAlign.Center
                ) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = tertiaryContainerLight
                ),
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun TheatherSchedule(){

    var fakeTheaters by remember {
        mutableStateOf(
            listOf(
                FakeTheather(
                    "Theather Name",
                    listOf("12:00", "15:00", "18:00", "21:00")
                ),
                FakeTheather(
                    "Theather Name",
                    listOf("12:00", "15:00", "18:00", "21:00")
                ),
                FakeTheather(
                    "Theather Name",
                    listOf("12:00", "15:00", "18:00", "21:00")
                )

            )
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {
        fakeTheaters.forEach { theater ->
            Text(
                text = theater.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                theater.schedules.forEach { schedule ->
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier.padding(4.dp),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            text = schedule,
                            color = onSurfaceVariantLight
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}