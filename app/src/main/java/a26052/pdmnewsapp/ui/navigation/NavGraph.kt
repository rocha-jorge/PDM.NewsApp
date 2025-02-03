package a26052.pdmnewsapp.ui.navigation

import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.domain.repository.ArticlesRepository
import a26052.pdmnewsapp.ui.bookmarks.BookmarksScreen
import a26052.pdmnewsapp.ui.bookmarks.BookmarksViewModel
import a26052.pdmnewsapp.ui.details.ArticleDetailScreen
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson

@Composable
fun NavGraph(navController: NavHostController, repository: ArticlesRepository) {
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                navController = navController, // ✅ Pass navController
                onArticleClick = { article ->
                    val safeArticle = article.copy(image_url = article.image_url ?: "")
                    val articleJson = Uri.encode(Gson().toJson(safeArticle))
                    Log.d("NAVIGATION", "Navigating to details: $articleJson")
                    navController.navigate("details/$articleJson")
                }
            )
        }

        composable("bookmarks") {
            val viewModel: BookmarksViewModel = hiltViewModel()
            BookmarksScreen(
                navController = navController,
                viewModel = viewModel,
                onArticleClick = { article ->
                    val safeArticle = article.copy(image_url = article.image_url ?: "")
                    val articleJson = Uri.encode(Gson().toJson(safeArticle))
                    navController.navigate("details/$articleJson")
                }
            )
        }

        composable(
            route = "details/{article}",
            arguments = listOf(navArgument("article") { type = NavType.StringType })
        ) { backStackEntry ->
            val articleJson = backStackEntry.arguments?.getString("article") ?: ""
            val article = Gson().fromJson(articleJson, Article::class.java)

            Log.d("NAVIGATION", "Opened Article Detail - Title: ${article.title}, Image URL: ${article.image_url}")

            ArticleDetailScreen(article, repository) // ✅ Pass repository
        }
    }
}


