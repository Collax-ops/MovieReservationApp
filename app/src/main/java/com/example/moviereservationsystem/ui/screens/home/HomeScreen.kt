package com.example.moviereservationsystem.ui.screens.home

import android.net.Uri
import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.moviereservationsystem.R
import com.example.moviereservationsystem.domain.model.Movie
import com.example.moviereservationsystem.ui.navigation.AppDestination
import com.example.moviereservationsystem.ui.screens.home.model.GenreUiModel
import com.example.moviereservationsystem.ui.screens.home.model.MovieUiModel
import com.example.moviereservationsystem.ui.screens.theme.inversePrimaryDark
import com.example.moviereservationsystem.ui.screens.theme.onPrimaryContainerLight
import com.example.moviereservationsystem.ui.screens.theme.onPrimaryLight
import com.example.moviereservationsystem.ui.screens.theme.tertiaryContainerLight


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedContentScope,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Home(modifier = Modifier, homeViewModel,sharedTransitionScope,navController,animatedVisibilityScope)
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    sharedTransitionScope: SharedTransitionScope,
    navController: NavController,
    animatedVisibilityScope: AnimatedContentScope,
) {

    val homeUiState by homeViewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopBar() },
        bottomBar = { NavigationBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            GenresFilter(
                genres = homeUiState.genres,
                selectedGenre = homeUiState.selectedGenre,
                onGenreSelected = { genreId ->
                    homeViewModel.updateSelectedGenre(genreId)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            with(sharedTransitionScope) {
                MovieGrid(
                    isLoading = homeUiState.isLoading,
                    movieList = homeUiState.filteredMovies,
                    navController = navController,
                    animatedVisibilityScope = animatedVisibilityScope
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
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
fun GenresFilter(
    genres: List<GenreUiModel>,
    selectedGenre: Int,
    onGenreSelected: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        userScrollEnabled = true
    ) {
        items(genres) { genre ->
            val isSelected = genre.id == selectedGenre
            FilterChip(
                selected = isSelected,
                onClick = { onGenreSelected(genre.id) },
                label = { Text(genre.name) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = tertiaryContainerLight
                ),
                leadingIcon = if (isSelected) {
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



@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MovieGrid(
    isLoading: Boolean,
    movieList: List<MovieUiModel>,
    navController: NavController,
    animatedVisibilityScope: AnimatedContentScope
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 100.dp),
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(movieList, key = { it.id }) { movie ->
                        MovieCard(
                            movieUiModel = movie,
                            sharedTransitionScope = this@MovieGrid,
                            animatedVisibilityScope = animatedVisibilityScope ,
                            navController = navController
                        )
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieCard(
    movieUiModel: MovieUiModel,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedContentScope,
    navController: NavController
) {

    Card(
        colors = CardDefaults.cardColors(containerColor = onPrimaryLight),
        modifier = Modifier.clickable {
            navController.navigate(AppDestination.MovieSchedule.createRoute(movieUiModel.id,
                Uri.encode(movieUiModel.posterPath)
            )) {
                popUpTo(AppDestination.Home.route) { inclusive = false }
                launchSingleTop = true
            }
        }
    ) {
        with(sharedTransitionScope){
            Column {
                Log.d("MovieCard", "Navigating with movieId: ${movieUiModel.id}")

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w500${movieUiModel.posterPath}")
                        .crossfade(true)
                        .placeholderMemoryCacheKey("${movieUiModel.id}")
                        .memoryCacheKey("${movieUiModel.id}")
                        .build(),
                    placeholder = null,
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp, 220.dp)
                        .sharedBounds(
                            rememberSharedContentState(
                                key = "${movieUiModel.id}"
                            ),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                        .clip(RoundedCornerShape(16.dp)),
                    onError = { Log.e("Coil", "Error loading image: ${it.result.throwable}") },
                    onSuccess = { Log.d("Coil", "Image loaded successfully") }
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
                    text = movieUiModel.title,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Composable
fun NavigationBar() {
    var selectedIndex by remember { mutableStateOf(0) }

    BottomAppBar(containerColor = onPrimaryLight) {
        NavigationBarItem(
            selected = selectedIndex == 0,
            onClick = { selectedIndex = 0 },
            icon = {
                Icon(
                    painterResource(R.drawable.home_icon),
                    contentDescription = "Home_Icon",
                )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent,
                selectedIconColor = inversePrimaryDark,
                unselectedIconColor = onPrimaryContainerLight
            )
        )
        Spacer(Modifier.width(8.dp))
        NavigationBarItem(
            selected = selectedIndex == 1,
            onClick = { selectedIndex = 1 },
            icon = {
                Icon(
                    painterResource(R.drawable.booking_icon),
                    contentDescription = "Booking_Icon"
                )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent,
                selectedIconColor = inversePrimaryDark,
                unselectedIconColor = Color.Black
            )
        )
        Spacer(Modifier.width(8.dp))
        NavigationBarItem(
            selected = selectedIndex == 2,
            onClick = { selectedIndex = 2 },
            icon = {
                Icon(
                    painterResource(R.drawable.history_icon),
                    contentDescription = "History_Icon"
                )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent,
                selectedIconColor = inversePrimaryDark,
                unselectedIconColor = Color.Black
            )
        )
    }
}

