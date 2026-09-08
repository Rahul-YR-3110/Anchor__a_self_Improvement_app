package com.example.anchor.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.outlined.LocalBar
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.anchor.ui.AppViewModelProvider
import com.example.anchor.ui.theme.AnchorTheme
import com.example.anchor.ui.theme.TextGray
import com.example.anchor.ui.viewmodels.HabitViewModel
import com.example.anchor.ui.viewmodels.JournalViewModel
import com.example.anchor.ui.viewmodels.WaterUiState
import com.example.anchor.ui.viewmodels.WaterViewModel
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDateTime

@Composable
fun HomeScreen(
    navController: NavController,
    waterViewModel: WaterViewModel = viewModel(factory = AppViewModelProvider.Factory),
    journalViewModel: JournalViewModel = viewModel(factory = AppViewModelProvider.Factory),
    habitViewModel: HabitViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val timestamp = LocalDateTime.now()
    val waterUiState by waterViewModel.waterUiState.collectAsState()
    val journalUiState by journalViewModel.journalUiState.collectAsState()
    val habitUiState by habitViewModel.habitUiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            GreetingHeader(timestamp)
            
            WaterIntake(
                currentGlass = waterUiState.currentGlasses,
                totalGoal = waterUiState.totalGoalGlasses,
                onIncrement = { waterViewModel.incrementWater() }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                JournalCard(
                    modifier = Modifier.weight(1f),
                    navController = navController,
                    entryCount = journalUiState.journalList.size
                )
                Spacer(modifier = Modifier.width(8.dp))
                AppBlockerCard(
                    modifier = Modifier.weight(1f),
                    navController = navController
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HabitTrackerCard(
                    navController = navController,
                    habitCount = habitUiState.habitItems.size
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun GreetingHeader(time: LocalDateTime) {
    val greetingtime: String = when (time.hour) {
        in 5..11 -> "Morning"
        in 12..16 -> "Afternoon"
        in 17..20 -> "Evening"
        else -> "Night"
    }
    Column(
        modifier = Modifier
            .padding(start = 15.dp, top = 15.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Good $greetingtime",
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            fontFamily = FontFamily.SansSerif,
            color = TextGray
        )
        Text(
            text = "Rahul",
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
            fontFamily = FontFamily.SansSerif,
        )
    }
}

@Composable
fun WaterIntake(
    currentGlass: Int,
    totalGoal: Int,
    onIncrement: () -> Unit
) {
    val glassFilled = Color(0xFF1E398A)
    Box(
        modifier = Modifier
            .padding(top = 20.dp, start = 5.dp, end = 5.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 28.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.WaterDrop,
                        contentDescription = null,
                        tint = glassFilled,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Water intake",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Spacer(modifier = Modifier.height(18.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.padding(bottom = 10.dp, start = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(totalGoal) { index ->
                        GlassIcon(filled = index < currentGlass)
                        if (index != totalGoal - 1) {
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }
                }
                IconButton(
                    onClick = onIncrement,
                    modifier = Modifier
                        .padding(bottom = 10.dp, end = 10.dp)
                        .size(40.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color(0xFF914D1B)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "add button",
                        tint = Color(0xFFFFFFFF),
                    )
                }
            }
        }
    }
}

@Composable
private fun GlassIcon(filled: Boolean) {
    val glassFilled = Color(0xFF1E398A)
    val glassEmpty = Color(0xFFEFC2AC)
    Icon(
        imageVector = if (filled) Icons.Filled.LocalBar else Icons.Outlined.LocalBar,
        contentDescription = if (filled) "Full Glass" else "Empty Glass",
        tint = if (filled) glassFilled else glassEmpty,
        modifier = Modifier.size(24.dp)
    )
}

@Composable
fun JournalCard(
    modifier: Modifier,
    navController: NavController,
    entryCount: Int
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .clickable { navController.navigate("journals") }
            .background(MaterialTheme.colorScheme.surface)
            .padding(18.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF3E3B5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.MenuBook,
                contentDescription = null,
                tint = Color(0xFF7A5A1E),
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = "Journal",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 10.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$entryCount entries",
            color = TextGray,
            fontSize = 13.sp
        )
    }
}

@Composable
fun AppBlockerCard(
    modifier: Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .clickable { navController.navigate("AppBlockerScreen") }
            .background(MaterialTheme.colorScheme.surface)
            .padding(18.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF2B7A5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Shield,
                contentDescription = null,
                tint = Color(0xFF7A2E1E),
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = "App blocker",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 10.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "2 apps blocked",
            color = TextGray,
            fontSize = 13.sp
        )
    }
}

@Composable
fun HabitTrackerCard(
    navController: NavController,
    habitCount: Int
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .clickable { navController.navigate("HabitTracker") }
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(18.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF3E3B5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = null,
                tint = Color(0xFF7A5A1E),
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = "Habit Tracker",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 10.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$habitCount habits tracked",
            color = TextGray,
            fontSize = 13.sp
        )
    }
}
@Composable
fun Taskspagecard(){

}
@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    AnchorTheme(darkTheme = true) {
        val fakeNavController = rememberNavController()
        HomeScreen(navController = fakeNavController)
    }
}
