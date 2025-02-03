package a26052.pdmnewsapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(navController: NavController, showBackButton: Boolean) {
    TopAppBar(
        title = { Text("NewsApp", color = Color.White) }, // ✅ Title text is white
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { navController.navigate("home") }) { // ✅ Navigate to Home
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate("bookmarks") }) { // ✅ Navigate to Bookmarks
                Icon(
                    imageVector = Icons.Outlined.Bookmark,
                    contentDescription = "Bookmarks",
                    tint = Color.White
                )
            }
        },
        modifier = Modifier.shadow(4.dp), // ✅ Adds shadow to the bar
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.DarkGray // ✅ Sets background to blue
        )
    )
}
