package com.example.moviereservationsystem.ui.screens.movieSchedule

import androidx.compose.animation.AnimatedContentScope
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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.UnstableApi
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.moviereservationsystem.R
import com.example.moviereservationsystem.ui.screens.home.TopBar
import com.example.moviereservationsystem.ui.screens.movieSchedule.model.DatesUiModel
import com.example.moviereservationsystem.ui.screens.theme.onSurfaceVariantLight
import com.example.moviereservationsystem.ui.screens.theme.tertiaryContainerLight



data class FakeTheather(
    val name: String,
    val schedules: List<String>,
    val isSelected: Boolean = false
)


@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieScheduleScreen(
    movieScheduleViewModel: MovieScheduleViewModel,
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
        MovieSchedule(
            movieScheduleViewModel,
            modifier = Modifier.align(Alignment.Center),
            sharedTransitionScope,
            animatedVisibilityScope,
            movieId,
            posterPath)
    }
}

@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieSchedule(
    movieScheduleViewModel: MovieScheduleViewModel,
    modifier: Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedContentScope,
    movieId: Int,
    posterPath: String,
){

    val movieScheduleUiState by movieScheduleViewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopBar() }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            MovieCard(sharedTransitionScope, animatedVisibilityScope, movieId, posterPath)
            DateList(movieScheduleUiState.dates,movieScheduleViewModel::updateSelectedDate)
            Spacer(modifier = Modifier.width(4.dp))
            TheatherSchedule()
        }
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
                }
            }
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieCard(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedContentScope,
    movieId: Int,
    posterPath: String,
){
    with(sharedTransitionScope) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.8f))
        ) {
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
                    .fillMaxWidth()
                    .aspectRatio(2f/3f)
                    .clip(RoundedCornerShape(16.dp))
                    .sharedBounds(
                        rememberSharedContentState(
                            key = "{$movieId}_image"
                        ),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
            )
        }
    }
}

@Composable
fun DateList(
    dates: List<DatesUiModel>,
    onDateSelected: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(dates.size) { index ->
            DateItem(dates[index]) { onDateSelected(index) }
        }
    }
}

@Composable
fun DateItem(
    date: DatesUiModel,
    onClick: () -> Unit
) {
    FilterChip(
        selected = date.isSelected,
        onClick = onClick,
        label = {
            Text(
                "${date.month}.\n" +
                        "${date.date}\n" +
                        "${date.dayOfWeek}",
                textAlign = TextAlign.Center
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = tertiaryContainerLight
        )
    )
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