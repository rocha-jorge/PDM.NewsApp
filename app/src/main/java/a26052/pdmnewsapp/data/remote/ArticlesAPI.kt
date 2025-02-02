package a26052.pdmnewsapp.data.remote

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import a26052.pdmnewsapp.domain.model.Article

class ArticlesAPI {
    private val client = OkHttpClient()

    fun fetchArticles(): List<Article> {
        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?country=us&category=sports&apiKey=YOUR_API_KEY")
            .build()

        val response = client.newCall(request).execute()
        val articles = mutableListOf<Article>()

        if (response.isSuccessful) {
            val body = response.body?.string()
            body?.let {
                val json = JSONObject(it)
                val jsonArray = json.getJSONArray("articles")

                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val article = Article(
                        id = null, // Null since it’s not stored in DB yet
                        title = jsonObject.getString("title"),
                        description = jsonObject.optString("description"),
                        imageUrl = jsonObject.optString("urlToImage"),
                        url = jsonObject.getString("url")
                    )
                    articles.add(article)
                }
            }
        }

        return articles
    }
}
