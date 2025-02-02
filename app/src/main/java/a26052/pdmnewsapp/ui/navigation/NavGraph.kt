package a26052.pdmnewsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import a26052.pdmnewsapp.ui.bookmarks.BookmarksScreen
import a26052.pdmnewsapp.ui.details.ArticleDetailScreen
import a26052.pdmnewsapp.ui.home.HomeScreen
import a26052.pdmnewsapp.domain.model.Article

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(onArticleClick = { article ->
                navController.navigate("details/${article.url}")
            })
        }
        composable("details/{articleUrl}") { backStackEntry ->
            val articleUrl = backStackEntry.arguments?.getString("articleUrl") ?: ""
            ArticleDetailScreen(Article(title = "", url = articleUrl, description = null, imageUrl = null))
        }
        composable("bookmarks") {
            BookmarksScreen(onArticleClick = { article ->
                navController.navigate("details/${article.url}")
            })
        }
    }
}
