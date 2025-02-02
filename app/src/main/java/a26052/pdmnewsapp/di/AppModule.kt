package a26052.pdmnewsapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import a26052.pdmnewsapp.data.local.AppDatabase
import a26052.pdmnewsapp.data.local.ArticleDao
import a26052.pdmnewsapp.data.remote.ArticlesAPI
import a26052.pdmnewsapp.data.repository.ArticlesRepositoryImpl
import a26052.pdmnewsapp.domain.repository.ArticlesRepository
import a26052.pdmnewsapp.domain.usecase.GetArticlesUseCase
import a26052.pdmnewsapp.domain.usecase.GetSavedArticlesUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGetSavedArticlesUseCase(repository: ArticlesRepository): GetSavedArticlesUseCase {
        return GetSavedArticlesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "news_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(database: AppDatabase): ArticleDao {
        return database.articleDao()
    }

    @Provides
    @Singleton
    fun provideArticlesAPI(): ArticlesAPI {
        return Retrofit.Builder()
            .baseUrl("https://newsdata.io/api/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArticlesAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideArticlesRepository(api: ArticlesAPI, dao: ArticleDao): ArticlesRepository {
        return ArticlesRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideGetArticlesUseCase(repository: ArticlesRepository): GetArticlesUseCase {
        return GetArticlesUseCase(repository)
    }
}
