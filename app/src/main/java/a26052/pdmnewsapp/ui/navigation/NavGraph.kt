package a26052.pdmnewsapp.ui.navigation

import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.domain.repository.ArticlesRepository
import a26052.pdmnewsapp.ui.bookmarks.BookmarksScreen
import a26052.pdmnewsapp.ui.bookmarks.BookmarksViewModel
import a26052.pdmnewsapp.ui.components.MyTopBar
import a26052.pdmnewsapp.ui.details.ArticleDetailScreen
import a26052.pdmnewsapp.ui.home.HomeViewModel
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                navController = navController,
                onArticleClick = { article ->
                    val safeArticle = article.copy(image_url = article.image_url ?: "")
                    val articleJson = Uri.encode(Gson().toJson(safeArticle))
                    navController.navigate("details/$articleJson")
                },
                viewModel = homeViewModel // ✅ Pass ViewModel
            )
        }


        composable("bookmarks") {
            Scaffold(
                topBar = { MyTopBar(navController, showBackButton = true) }
            ) { paddingValues ->
                val viewModel: BookmarksViewModel = hiltViewModel()
                BookmarksScreen(
                    navController = navController,
                    viewModel = viewModel,
                    onArticleClick = { article ->
                        val safeArticle = article.copy(image_url = article.image_url ?: "")
                        val articleJson = Uri.encode(Gson().toJson(safeArticle))
                        navController.navigate("details/$articleJson")
                    },
                    modifier = Modifier.padding(paddingValues) // ✅ Apply padding
                )
            }
        }

        composable(
            route = "details/{article}",
            arguments = listOf(navArgument("article") { type = NavType.StringType })
        ) { backStackEntry ->
            val articleJson = backStackEntry.arguments?.getString("article") ?: ""
            val article = Gson().fromJson(articleJson, Article::class.java)

            Scaffold(
                topBar = { MyTopBar(navController, showBackButton = true) }
            ) { paddingValues ->
                ArticleDetailScreen(
                    article = article,
                    repository = repository,
                    modifier = Modifier.padding(paddingValues) // ✅ Apply padding
                )
            }
        }
    }
}


