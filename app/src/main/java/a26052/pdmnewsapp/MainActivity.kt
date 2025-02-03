package a26052.pdmnewsapp

import a26052.pdmnewsapp.domain.repository.ArticlesRepository
import a26052.pdmnewsapp.ui.navigation.NavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: ArticlesRepository // Ensure repository is injected

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            // ✅ Ensure NavGraph is displayed
            NavGraph(navController, repository)
        }
    }
}
