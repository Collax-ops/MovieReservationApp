package com.example.moviereservationsystem.ui.screens.seat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviereservationsystem.R
import com.example.moviereservationsystem.ui.screens.seat.model.SeatUiState
import androidx.compose.runtime.getValue
import kotlin.compareTo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeatScreen(
    viewModel: SeatViewModel,
    movieId: Int,
    theaterId: Int
) {
    LaunchedEffect(Unit) {
        viewModel.loadSeats(theaterId)
    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    )
    {
        Seat(Modifier.align(Alignment.Center),viewModel)

    }
}

@Composable
fun Seat(
    modifier: Modifier = Modifier,
    seatViewModel: SeatViewModel
) {
    val seatUiState by seatViewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopBar() }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues) // Padding from Scaffold
                .padding(16.dp),       // Your overall content padding
            // verticalArrangement = Arrangement.SpaceBetween // Pushes bottom elements down
        ) {
            // This Column will group the screen and grid, and we can center it or make it take available space
            Column(
                modifier = Modifier
                    .weight(1f) // This inner Column takes up available space before legend/button
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally, // Center children horizontally
                verticalArrangement = Arrangement.Center // Center children vertically in this Column
            ) {
                CinemaScreenCurve(
                    modifier = Modifier.fillMaxWidth(), // Already fills width
                    curveHeight = 50.dp,
                    curveColor = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(16.dp)) // Space between curve and grid

                // SeatGrid might not need a weight here if the parent Column handles distribution
                SeatGrid(
                    seats = seatUiState.seats,
                    onSeatClick = { seatViewModel.onSeatClick(it) },
                    modifier = Modifier.fillMaxWidth() // Ensure it fills width; vertical size is flexible
                    // .weight(1f) // Add this back if SeatGrid needs to expand within this centered block
                )
            }

            // Elements at the bottom
            SeatLegend(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp) // Add some spacing if needed
            )

            ProceedToPaymentButton(
                selectedCount = seatUiState.selectedCount,
                enabled = seatUiState.selectedCount > 0,
                onClick = { seatViewModel.onProceed() },
                modifier = Modifier.fillMaxWidth()
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
                    Text(
                        "Seat \n" + "Booking"
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

@Preview(showBackground = true)
@Composable
fun CinemaScreenCurve(
    modifier: Modifier = Modifier,
    curveHeight: Dp = 40.dp,
    curveColor: Color = Color(0xFF333333)
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Screen",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(curveHeight)
        ) {
            drawScreenCurve(curveColor)
        }
    }
}

private fun DrawScope.drawScreenCurve(curveColor: Color) {
    val w = size.width
    val h = size.height

    val path = Path().apply {
        moveTo(0f, h)
        quadraticTo(x1 = w / 2, y1 = 0f, x2 = w, y2 = h)
        lineTo(w, h)
        lineTo(0f, h)
        close()
    }

    drawPath(
        path = path,
        color = curveColor
    )
}

@Composable
fun SeatGrid(
    seats: List<SeatUiState>,
    onSeatClick: (String) -> Unit,
    modifier: Modifier = Modifier
){
    val rows = seats.groupBy { it.row }.toSortedMap()
    Column(modifier = modifier) {
        rows.forEach { (rowChar, rowSeats) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "$rowChar", modifier = Modifier.padding(end = 8.dp))
                rowSeats.sortedBy { it.number }
                    .forEach { seat ->
                        SeatItem(
                            uiState = seat,
                            onClick = { onSeatClick(seat.seatId) }
                        )
                    }
            }
        }
    }
}

@Composable
fun SeatItem(
    uiState: SeatUiState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Define color según estado
    val color = when {
        !uiState.isAvailable      -> Color.DarkGray
        uiState.isSelected        -> Color(0xFF4CAF50)
        else                       -> Color.LightGray
    }
    Box(
        modifier = modifier
            .size(32.dp)
            .padding(4.dp)
            .background(color, shape = RoundedCornerShape(4.dp))
            .clickable(enabled = uiState.isAvailable, onClick = onClick)
    )
}

@Preview(showBackground = true)
@Composable
fun SeatLegend(
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
        LegendItem(color = Color(0xFFD9D9D9), label = "Available")
        LegendItem(color = Color(0xFF2F312A),  label = "Occupied")
        LegendItem(color = Color(0xFFB1D18A), label = "Your selection")
    }
}

@Composable
private fun LegendItem(
    color: Color,
    label: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(color = color, shape = RoundedCornerShape(4.dp))
        )
        Spacer(Modifier.width(4.dp))
        Text(text = label, fontSize = 14.sp)
    }
}

@Composable
fun ProceedToPaymentButton(
    selectedCount: Int,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) Color(0xFFBCECE7) else Color.LightGray,
            contentColor = Color.White
        )
    ) {
        val text = if (selectedCount > 0) {
            "Proceed to Payment ($selectedCount)"
        } else {
            "Proceed to Payment"
        }
        Text(text = text, fontSize = 16.sp)
    }
}

