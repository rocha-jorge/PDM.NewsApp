package a26052.pdmnewsapp.ui.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.domain.usecase.GetSavedArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val getSavedArticlesUseCase: GetSavedArticlesUseCase
) : ViewModel() {

    private val _savedArticles = MutableStateFlow<List<Article>>(emptyList())
    val savedArticles: StateFlow<List<Article>> = _savedArticles

    init {
        fetchSavedArticles()
    }

    private fun fetchSavedArticles() {
        viewModelScope.launch {
            getSavedArticlesUseCase { articles ->
                _savedArticles.value = articles
            }
        }
    }
}
