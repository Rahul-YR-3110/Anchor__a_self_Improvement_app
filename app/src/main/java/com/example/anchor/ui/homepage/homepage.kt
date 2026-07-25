package com.example.anchor.ui.homepage
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material.icons.outlined.LocalBar
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.anchor.ui.theme.AnchorTheme
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.WineBar
import androidx.compose.material3.IconButton
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.remember
import com.example.anchor.ui.theme.TextGray
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.ui.text.style.LineHeightStyle

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier=Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background))
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top=20.dp)
        ){
            Spacer(modifier = Modifier.height(24.dp))
            GreetingHeader()
            WaterIntake()

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                JournalCard(modifier = Modifier.weight(1f), navController = navController)
                Spacer(modifier = Modifier.width(8.dp))
                AppBlockerCard(modifier = Modifier.weight(1f),navController = navController)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                HabitTrackerCard(navController)
            }



            Spacer(modifier = Modifier.height(20.dp))
        }

    }
}


@Composable
fun GreetingHeader(){
    Column(modifier = Modifier
        .padding(start = 15.dp, top = 15.dp)
        .fillMaxWidth()) {
        Text(
            "Good morning",
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            fontFamily = FontFamily.SansSerif,
            color = TextGray
        )
        Text(
            "Rahul",
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
            fontFamily = FontFamily.SansSerif,

        )
    }
}
@Composable
fun WaterIntake() {
    val GlassFilled = Color(0xFF1E398A)
    val GlassEmpty = Color(0xFFEFC2AC)
    var currentglass by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .padding(top = 20.dp, start = 5.dp, end = 5.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 28.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    )
    {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp)){
                    Icon(
                        imageVector = Icons.Outlined.WaterDrop,
                        contentDescription = null,
                        tint = GlassFilled,
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
                    val totalCount = 8

                    repeat(totalCount) { index ->
                        GlassIcon(filled = index < currentglass)
                        if (index != totalCount - 1) {
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }
                }
                IconButton(onClick = {currentglass+=1},
                    modifier = Modifier.padding(bottom = 10.dp, end = 10.dp).size(40.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color(0xFF914D1B)
                    )
                ){
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
    val GlassFilled = Color(0xFF1E398A)
    val GlassEmpty = Color(0xFFEFC2AC)
    Icon(
        imageVector = if (filled) Icons.Filled.LocalBar else Icons.Outlined.LocalBar,
        contentDescription = if (filled) "Full Glass" else "Empty Glass",
        tint = if (filled) GlassFilled else GlassEmpty,
        modifier = Modifier.size(24.dp)
    )
}
@Composable
fun JournalCard(modifier: Modifier,navController: NavController ) {
    Column(modifier=Modifier
        .clip(RoundedCornerShape(24.dp))
        .clickable {
            navController.navigate("notes")
        }
        .background(MaterialTheme.colorScheme.surface)
        .padding(18.dp)
    ){
        Box(modifier=Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF3E3B5)),
            contentAlignment = Alignment.Center
        ){
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
            text = "3 entries this week",
            color = TextGray,
            fontSize = 13.sp
        )
    }
}
@Composable
fun AppBlockerCard(modifier: Modifier,navController: NavController ) {
    Column(modifier = modifier
        .clip(RoundedCornerShape(24.dp))
        .background(MaterialTheme.colorScheme.surface)
        .padding(18.dp)
    ){
        Box(modifier=Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF2B7A5)),
            contentAlignment = Alignment.Center
        ){
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
fun HabitTrackerCard(navController: NavController){
    Column(modifier=Modifier
        .clip(RoundedCornerShape(24.dp))
        .clickable {
            navController.navigate("HabitTracker")
        }
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surface)
        .padding(18.dp)
    ){
        Box(modifier=Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF3E3B5)),
            contentAlignment = Alignment.Center
        ){
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
            text = "3 entries this week",
            color = TextGray,
            fontSize = 13.sp
        )
    }
}
@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {

        AnchorTheme(darkTheme=true) {
            val fakeNavController = rememberNavController()
            HomeScreen(navController = fakeNavController)
        }
    }

