package a26052.pdmnewsapp.ui.bookmarks

import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.domain.repository.ArticlesRepository
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val repository: ArticlesRepository
) : ViewModel() {

    private val _bookmarkedArticles = MutableStateFlow<List<Article>>(emptyList())
    val bookmarkedArticles = _bookmarkedArticles.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getBookmarkedArticles().collectLatest { articles ->
                Log.d("BOOKMARK_DEBUG", "ViewModel collected ${articles.size} articles")
                _bookmarkedArticles.value = articles
            }
        }
    }

}
