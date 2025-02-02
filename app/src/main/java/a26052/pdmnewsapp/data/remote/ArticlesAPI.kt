package a26052.pdmnewsapp.data.remote

import a26052.pdmnewsapp.domain.model.Article
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesAPI {

    @GET("latest")
    suspend fun fetchArticles(
        @Query("apikey") apiKey: String
    ): NewsResponse

    companion object {
        private const val BASE_URL = "https://newsdata.io/api/1/"

        fun create(): ArticlesAPI {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ArticlesAPI::class.java)
        }
    }
}
