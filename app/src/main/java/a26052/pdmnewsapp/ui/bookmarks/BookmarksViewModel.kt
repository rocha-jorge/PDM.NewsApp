package a26052.pdmnewsapp.ui.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.domain.usecase.GetSavedArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val getSavedArticlesUseCase: GetSavedArticlesUseCase
) : ViewModel() {

    private val _bookmarkedArticles = MutableStateFlow<List<Article>>(emptyList())
    val bookmarkedArticles = _bookmarkedArticles.asStateFlow()

    init {
        viewModelScope.launch {
            getSavedArticlesUseCase().collect { articles ->
                _bookmarkedArticles.value = articles
            }
        }
    }
}
