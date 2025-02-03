package a26052.pdmnewsapp.ui.navigation

import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.ui.details.ArticleDetailScreen
import a26052.pdmnewsapp.ui.home.HomeScreen
import android.net.Uri // ✅ Use this import for encoding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onArticleClick = { article ->
                    val articleJson = Uri.encode(Gson().toJson(article)) // ✅ Correct encoding
                    navController.navigate("details/$articleJson")
                }
            )
        }

        composable(
            route = "details/{article}",
            arguments = listOf(navArgument("article") { type = NavType.StringType })
        ) { backStackEntry ->
            val articleJson = backStackEntry.arguments?.getString("article") ?: ""
            val article = Gson().fromJson(articleJson, Article::class.java) // ✅ Convert back to object

            ArticleDetailScreen(article) // ✅ Pass full article object
        }
    }
}
