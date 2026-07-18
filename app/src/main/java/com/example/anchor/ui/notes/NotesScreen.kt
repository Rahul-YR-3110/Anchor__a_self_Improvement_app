package com.example.anchor.ui.notes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.tooling.preview.Preview
import com.example.anchor.ui.theme.AnchorTheme

@Composable
fun NotesScreen() {
    val noteInput= remember{ mutableStateOf("") }
    val notesList=remember { mutableStateListOf<String>() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 40.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = noteInput.value,
                onValueChange = { newValue ->
                    noteInput.value = newValue
                },
                placeholder = { Text("Enter a note...") },
                modifier = Modifier.weight(1f),
                shape= RoundedCornerShape(10.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (noteInput.value.isNotBlank()) {
                        notesList.add(noteInput.value)
                        noteInput.value = ""
                    }
                }
            ) {
                Text("Add")
            }
        }
        Spacer(modifier = Modifier.height(16.dp)) // Gap below the row
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(notesList) { singleNote ->
                Text(
                    text = singleNote,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotesScreenPreview() {
    AnchorTheme {
        NotesScreen()
    }
}