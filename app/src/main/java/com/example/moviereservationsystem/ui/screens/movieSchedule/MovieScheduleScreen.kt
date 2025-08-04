package com.example.moviereservationsystem.ui.screens.movieSchedule

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.moviereservationsystem.R
import com.example.moviereservationsystem.ui.navigation.AppDestination
import com.example.moviereservationsystem.ui.screens.home.TopBar
import com.example.moviereservationsystem.ui.screens.movieSchedule.model.DatesUiModel
import com.example.moviereservationsystem.ui.screens.movieSchedule.model.MovieScheduleUiModel
import com.example.moviereservationsystem.ui.screens.movieSchedule.model.TheatersUiModel
import com.example.moviereservationsystem.ui.screens.theme.tertiaryContainerLight

@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieScheduleScreen(
    movieScheduleViewModel: MovieScheduleViewModel,
    movieId: Int,
    posterPath: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedContentScope,
    navController: NavHostController,
){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        MovieSchedule(
            movieScheduleViewModel = movieScheduleViewModel,
            modifier = Modifier.align(Alignment.Center),
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope,
            movieId = movieId,
            posterPath = posterPath,
            navController = navController
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieSchedule(
    movieScheduleViewModel: MovieScheduleViewModel,
    modifier: Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedContentScope,
    movieId: Int,
    posterPath: String,
    navController: NavController
) {
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
            DateList(movieScheduleUiState.dates, {movieScheduleViewModel.updateSelectedDate(it)})
            Spacer(modifier = Modifier.width(4.dp))
            TheaterListWithDividers(
                theaters = movieScheduleUiState.theaters,
                movieScheduleUiState = movieScheduleUiState,
                onTheaterSelected = { movieScheduleViewModel.updateSelectedTheater(it) },
                onToggleExpandedState = { movieScheduleViewModel.toggleExpandedState(it) },
                movieId = movieId,
                navController = navController
            )
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


@Composable
fun TheaterListWithDividers(
    theaters: List<TheatersUiModel>,
    movieScheduleUiState: MovieScheduleUiState,
    onTheaterSelected: (Int) -> Unit,
    onToggleExpandedState: (Int) -> Unit,
    movieId: Int,
    navController: NavController
) {
    LazyColumn {
        items(theaters) { theater ->
            ExpandableTheaterItemCombined(
                theater = theater,
                onTheaterSelected = { onTheaterSelected(theater.theaterId) },
                schedules = movieScheduleUiState.movieSchedules,
                isExpanded = movieScheduleUiState.expandedState[theater.theaterId] ?: false,
                onToggleExpandedState = { onToggleExpandedState(theater.theaterId) },
                movieId = movieId,
                navController = navController
            )
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExpandableTheaterItemCombined(
    theater: TheatersUiModel,
    onTheaterSelected: (Int) -> Unit,
    schedules: List<MovieScheduleUiModel>,
    isExpanded: Boolean,
    onToggleExpandedState: (Int) -> Unit,
    movieId: Int,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onToggleExpandedState(theater.theaterId)
                    onTheaterSelected(theater.theaterId)
                }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = theater.theaterName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.ArrowDropDown,
                contentDescription = if (isExpanded) "Contraer ${theater.theaterName}" else "Expandir ${theater.theaterName}"
            )
        }

        if (isExpanded) {
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                schedules.forEach { schedule ->
                    OutlinedButton(
                        onClick = {
                            navController.navigate(
                                AppDestination.Seat.createRoute(
                                    theatherId = theater.theaterId,
                                    scheduleId = schedule.scheduleId
                                )
                            )
                        },
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            text = "${schedule.starTime} - ${schedule.endTime}",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
