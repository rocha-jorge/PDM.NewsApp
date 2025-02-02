package a26052.pdmnewsapp.data.repository

import a26052.pdmnewsapp.data.local.ArticleDao
import a26052.pdmnewsapp.data.local.ArticleEntity
import a26052.pdmnewsapp.data.remote.ArticlesAPI
import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.domain.repository.ArticlesRepository

class ArticlesRepositoryImpl(
    private val api: ArticlesAPI,
    private val dao: ArticleDao
) : ArticlesRepository {
    override suspend fun getArticles(): List<Article> {
        return api.fetchArticles()
    }

    override suspend fun saveArticle(article: Article) {
        dao.insertArticle(
            ArticleEntity(
                title = article.title,
                description = article.description,
                imageUrl = article.imageUrl,
                url = article.url
            )
        )
    }

    override suspend fun deleteArticle(article: Article) {
        dao.deleteArticle(
            ArticleEntity(
                id = article.id ?: 0, // Convert to Entity with id
                title = article.title,
                description = article.description,
                imageUrl = article.imageUrl,
                url = article.url
            )
        )
    }

    override fun getSavedArticles(callback: (List<Article>) -> Unit) {
        val entities = dao.getAllArticles()
        val articles = entities.map {
            Article(
                id = it.id,
                title = it.title,
                description = it.description,
                imageUrl = it.imageUrl,
                url = it.url
            )
        }
        callback(articles)
    }
}
