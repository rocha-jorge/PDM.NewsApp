package a26052.pdmnewsapp.data.repository

import a26052.pdmnewsapp.data.local.ArticleDao
import a26052.pdmnewsapp.data.local.ArticleEntity
import a26052.pdmnewsapp.data.remote.ArticlesAPI
import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.domain.repository.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val api: ArticlesAPI,
    private val dao: ArticleDao
) : ArticlesRepository {

    override suspend fun getArticles(): List<Article> {
        return try {
            val response = api.fetchArticles("pub_67564d3016ba0b01637fb7ea501c9bd4ada1a")
            response.articles ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
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
                id = article.id ?: 0,
                title = article.title,
                description = article.description,
                imageUrl = article.imageUrl,
                url = article.url
            )
        )
    }

    override fun getSavedArticlesFlow(): Flow<List<Article>> {
        return dao.getAllArticlesFlow().map { entities ->
            entities.map {
                Article(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    imageUrl = it.imageUrl,
                    url = it.url
                )
            }
        }
    }
}
