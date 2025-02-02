package a26052.pdmnewsapp.data.repository

import a26052.pdmnewsapp.data.local.ArticleDao
import a26052.pdmnewsapp.data.local.ArticleEntity
import a26052.pdmnewsapp.data.remote.ArticlesAPI
import a26052.pdmnewsapp.domain.model.Article
import a26052.pdmnewsapp.domain.repository.ArticlesRepository
import a26052.pdmnewsapp.data.toEntity
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val api: ArticlesAPI,
    private val articleDao: ArticleDao
) : ArticlesRepository {

    override suspend fun getArticles(): List<Article> {
        val apiResponse = api.fetchArticles().results ?: emptyList()

        return apiResponse.map { articleDto ->
            Article(
                title = articleDto.title ?: "No Title",
                description = articleDto.description ?: "No Description",
                url = articleDto.url ?: "#",
                imageUrl = articleDto.imageUrl
            )
        }
    }


    override suspend fun saveArticle(article: Article) {
        articleDao.insertArticle(ArticleEntity(
            title = article.title,
            description = article.description,
            url = article.url,
            imageUrl = article.imageUrl
        ))
    }

    override suspend fun deleteArticle(article: Article) {
        articleDao.deleteArticle(ArticleEntity(
            title = article.title,
            description = article.description,
            url = article.url,
            imageUrl = article.imageUrl
        ))
    }

    override fun getSavedArticlesFlow(): Flow<List<Article>> {
        return articleDao.getAllArticlesFlow().map { entities ->
            entities.map { entity ->
                Article(
                    title = entity.title,
                    description = entity.description,
                    url = entity.url,
                    imageUrl = entity.imageUrl
                )
            }
        }
    }
}
