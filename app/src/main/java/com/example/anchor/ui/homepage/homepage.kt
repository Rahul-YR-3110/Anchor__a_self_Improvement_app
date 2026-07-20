package com.example.anchor.ui.homepage
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier=Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background))
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
            Spacer(modifier = Modifier.height(24.dp))
            GreetingHeader()

            Spacer(modifier = Modifier.height(20.dp))
            WaterIntake()

            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier=Modifier.fillMaxWidth()){
                //JournalCard(modifier= Modifier.weight(1f))
                Spacer(modifier = Modifier.width(16.dp))
                //AppBlockerCard(modifier= Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(16.dp))
            //StreakRow()

            Spacer(modifier = Modifier.height(20.dp))
        }

    }
}


@Composable
fun GreetingHeader(){
    Column(modifier = Modifier
        .padding(horizontal = 10.dp, vertical = 50.dp)
        .fillMaxWidth()) {
        Text(
            "Good",
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            fontFamily = FontFamily.SansSerif
        )
        Text(
            "Rahul",
            fontWeight = FontWeight.Medium,
            fontSize = 25.sp,
            fontFamily = FontFamily.SansSerif
        )
    }
}
@Composable
fun WaterIntake() {
    val GlassFilled = Color(0xFFD9531E)
    val GlassEmpty = Color(0xFFEFC2AC)
    Box(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 28.dp))
            .background(Color(0xFFD57447))
    )
    {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.WaterDrop,
                        contentDescription = null,
                        tint = GlassFilled,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Water intake",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = "Settings",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(18.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val totalCount = 8
                    val filledCount = 4
                    repeat(totalCount) { index ->
                        GlassIcon(filled = index < filledCount)
                        if (index != totalCount - 1) {
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }
                }
            }
        }
    }
}
@Composable
private fun GlassIcon(filled: Boolean) {
    val GlassFilled = Color(0xFFD9531E)
    val GlassEmpty = Color(0xFFEFC2AC)
    Icon(
        imageVector = Icons.Outlined.WineBar,
        contentDescription = null,
        tint = if (filled) GlassFilled else GlassEmpty,
        modifier = Modifier.size(22.dp)
    )
}
/*
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.navigate("notes") },
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp),
                shape = RoundedCornerShape(size = 20.dp)
            ) {
                Text(
                    "Journal",
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Left
                )
            }
            Button(onClick={navController.navigate("appblocker")},
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp),
                shape = RoundedCornerShape(size = 20.dp)
            ) {
                Text(
                    "App Blocker",
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Left
                )
            }
        }
    }
}
 */
@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    AnchorTheme{
        val fakeNavController = rememberNavController()
        HomeScreen(navController = fakeNavController)
    }
}
