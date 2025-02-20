package a26052.pdmnewsapp.ui.home

import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.domain.repository.ArticlesRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ArticlesRepository
) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles // ✅ Expose as StateFlow

    init {
        viewModelScope.launch {
            _articles.value = repository.getArticles() // ✅ Fetch articles
        }
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            try {
                val fetchedArticles = repository.getArticles()
                _articles.value = fetchedArticles
            } catch (e: Exception) {
                e.printStackTrace() // Log error if fetch fails
            }
        }
    }
}
