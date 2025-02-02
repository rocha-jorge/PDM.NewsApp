package a26052.pdmnewsapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.domain.usecase.GetArticlesUseCase
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            try {
                val response = getArticlesUseCase()
                Log.d("API_RESPONSE_RAW", "🌐 Full API response: $response")

                if (response.isNotEmpty()) {
                    _articles.value = response
                } else {
                    Log.w("API_WARNING", "⚠️ No articles found in the response!")
                }

            } catch (e: Exception) {
                Log.e("API_ERROR", "❌ Error fetching articles: ${e.message}")
            }
        }
    }

}
