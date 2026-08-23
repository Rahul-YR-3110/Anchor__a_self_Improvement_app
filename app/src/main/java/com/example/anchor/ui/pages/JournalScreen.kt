package com.example.anchor.ui.pages
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anchor.ui.theme.AnchorTheme
import java.time.LocalDate
import java.util.UUID

enum class Mood(val label:String, val icon: ImageVector){
    SUNNY("Sunny", Icons.Default.WbSunny),
    CLOUDY("Cloudy", Icons.Default.Cloud),
    NIGHT("Night", Icons.Default.NightsStay)
}
data class JournalEntry(
    val id: String = UUID.randomUUID().toString(),
    val notes: String,
    val timestamp: LocalDateTime= LocalDateTime.now(),
    val mood: Mood
)
fun LocalDateTime.toRelativeFormattedString(): String {
    val today = LocalDate.now()
    val entryDate = this.toLocalDate()
    val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")
    val timeString = this.format(timeFormatter).lowercase()

    return when {
        entryDate.isEqual(today) -> "Today, $timeString"
        entryDate.isEqual(today.minusDays(1)) -> "Yesterday, $timeString"
        else -> "${this.format(DateTimeFormatter.ofPattern("EEE"))}, $timeString"
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalScreen() {
    val backgroundColor = MaterialTheme.colorScheme.background
    val fabColor = MaterialTheme.colorScheme.primary

    // Sample initial list matching the design preview
    val journalList = remember { mutableStateListOf<JournalEntry>() }
    var showAddSheet by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = backgroundColor,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddSheet = true },
                containerColor = fabColor,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Journal Entry",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Journal",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // SUBHEADER COUNTER
            Text(
                text = "${journalList.size} entries this week",
                fontSize = 15.sp,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier.padding(start = 12.dp, bottom = 16.dp)
            )

            // ENTRY LIST
            if (journalList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No entries yet! Tap the + button to add one.",
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    itemsIndexed(journalList, key = { _, entry -> entry.id }) { index, entry ->
                        val isLatest = index == 0
                        JournalEntryCard(entry = entry, isHighlighted = isLatest)
                    }
                }
            }
        }
        if (showAddSheet) {
            AddJournalBottomSheet(
                onDismiss = { showAddSheet = false },
                onSave = { notes, selectedMood ->
                    journalList.add(0, JournalEntry(notes = notes, mood = selectedMood))
                    showAddSheet = false
                }
            )
        }
    }
}
@Composable
fun JournalEntryCard(entry: JournalEntry, isHighlighted: Boolean) {
    val cardBackground = if (isHighlighted) Color(0xFFFBF0EA) else Color(0xFF2C2C2C)
    val titleColor = if (isHighlighted) Color(0xFF5C1E0A) else Color.White
    val textColor = if (isHighlighted) Color(0xFF6E2D18) else Color.White.copy(alpha = 0.8f)
    val iconTint = if (isHighlighted) Color(0xFF5C1E0A) else Color.White.copy(alpha = 0.7f)
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackground),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = entry.timestamp.toRelativeFormattedString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = titleColor
                )
                Icon(
                    imageVector = entry.mood.icon,
                    contentDescription = entry.mood.label,
                    tint = iconTint,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = entry.notes,
                fontSize = 15.sp,
                lineHeight = 22.sp,
                color = textColor,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddJournalBottomSheet(
    onDismiss: () -> Unit,
    onSave: (notes: String, mood: Mood) -> Unit
) {
    var noteText by remember { mutableStateOf("") }
    var selectedMood by remember { mutableStateOf(Mood.SUNNY) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF2C2C2C),
        contentColor = Color.White,
        dragHandle = {
            BottomSheetDefaults.DragHandle(
                color = MaterialTheme.colorScheme.primary
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "New Reflection",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "How are you feeling?",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.7f)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Mood.entries.forEach { mood ->
                    FilterChip(
                        selected = (selectedMood == mood),
                        onClick = { selectedMood = mood },
                        label = { Text(mood.label, color = Color.White) },
                        leadingIcon = {
                            Icon(
                                imageVector = mood.icon,
                                contentDescription = mood.label,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    )
                }
            }

            OutlinedTextField(
                value = noteText,
                onValueChange = { noteText = it },
                placeholder = { Text("Write your thoughts...", color = Color.Gray) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                maxLines = 6
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton (onClick = onDismiss) {
                    Text("Cancel", color = Color.Gray)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (noteText.isNotBlank()) {
                            onSave(noteText, selectedMood)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD85834)),
                    enabled = noteText.isNotBlank()
                ) {
                    Text("Save", color = Color.White)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun JournalScreenPreview() {
    AnchorTheme(darkTheme=true) {
        JournalScreen()
    }
}
