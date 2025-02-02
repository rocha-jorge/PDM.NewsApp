package a26052.pdmnewsapp.domain.repository

import a26052.pdmnewsapp.domain.model.Article

interface ArticlesRepository {
    suspend fun getArticles(): List<Article> // Fetch articles from API
    suspend fun saveArticle(article: Article) // Save an article to DB
    suspend fun deleteArticle(article: Article) // Delete an article from DB
    fun getSavedArticles(callback: (List<Article>) -> Unit) // Fetch saved articles using a callback
}
