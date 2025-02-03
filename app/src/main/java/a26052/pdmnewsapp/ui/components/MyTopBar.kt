package a26052.pdmnewsapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark // ✅ Use "filled.Bookmark"
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(navController: NavController) {
    TopAppBar(
        title = { Text("NewsApp") },
        actions = {
            IconButton(onClick = { navController.navigate("bookmarks") }) {
                Icon(imageVector = Icons.Filled.Bookmark, contentDescription = "Bookmarks") // ✅ Use "Filled.Bookmark"
            }
        }
    )
}
