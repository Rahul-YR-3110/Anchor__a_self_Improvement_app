package com.example.anchor.ui.homepage
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.material3.FilledIconButton
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import com.example.anchor.ui.theme.AnchorTheme

@Composable
fun HomeScreen(navController: NavController){
    var glasses by remember { mutableStateOf(0) }
    Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 50.dp).fillMaxWidth()) {
        Text("Good Morning",
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            fontFamily = FontFamily.SansSerif
        )
        Text("Name",
            fontWeight = FontWeight.Medium ,
            fontSize = 25.sp,
            fontFamily = FontFamily.SansSerif
        )
        Box(
            modifier = Modifier
                .padding(top = 20.dp)
                .height(200.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(size = 16.dp))
                .background(Color(0xFFF9EBE6))
        )
        {
            Column() {
                Text(
                    "Water Intake",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,

                ) {
                    Text("$glasses glasses", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 20.sp)
                    FilledIconButton(
                        onClick = { glasses += 1 },
                        modifier = Modifier.size(40.dp),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "+",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }
                }

            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.navigate("notes") },
                modifier = Modifier.weight(1f).height(150.dp),
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
                modifier = Modifier.weight(1f).height(150.dp),
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
@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    AnchorTheme{
        val fakeNavController = rememberNavController()
        HomeScreen(navController = fakeNavController)
    }
}
