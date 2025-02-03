package a26052.pdmnewsapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(navController: NavController) {
    TopAppBar(
        title = { Text("NewsApp") },
        actions = {
            IconButton(onClick = { navController.navigate("bookmarks") }) {
                Icon(imageVector = Icons.Outlined.Bookmark, contentDescription = "Bookmarks")
            }
        }
    )
}
