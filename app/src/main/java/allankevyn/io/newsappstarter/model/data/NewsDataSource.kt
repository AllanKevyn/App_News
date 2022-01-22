package allankevyn.io.newsappstarter.model.data

import android.content.Context
import allankevyn.io.newsappstarter.model.Article
import allankevyn.io.newsappstarter.model.db.ArticleDataBase
import allankevyn.io.newsappstarter.network.RetrofitInstance
import allankevyn.io.newsappstarter.presenter.favorite.FavoriteHome
import allankevyn.io.newsappstarter.presenter.news.NewsHome
import allankevyn.io.newsappstarter.presenter.search.SearchHome
import kotlinx.coroutines.*

class NewsDataSource(context: Context) {

    private var db : ArticleDataBase = ArticleDataBase(context)
    private var newsRepository: NewsRepository = NewsRepository(db)

    fun getBreakingNews(callback: NewsHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.getBreakingNew("br")
            if (response.isSuccessful) {
                response.body()?.let {
                    callback.onSuccess(it)
                }
                callback.onComplete()
            } else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }

    fun searchNews(term: String, callback: SearchHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.searchNews(term)
            if (response.isSuccessful) {
                response.body()?.let {
                    callback.onSuccess(it)
                }
                callback.onComplete()
            } else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }

    fun saveArticle(article: Article){
        GlobalScope.launch(Dispatchers.Main) {
            newsRepository.updateInsert(article)
        }
    }

    fun getAllArticle(callback: FavoriteHome.Presenter){
        var allArticles: List<Article>
        CoroutineScope(Dispatchers.IO).launch {
            allArticles = newsRepository.getAll()

            withContext(Dispatchers.Main){
                callback.onSuccess(allArticles)
            }
        }
    }

    fun deleteArticle(article: Article?){
        GlobalScope.launch(Dispatchers.Main) {
            article?.let {articleSafe ->
                newsRepository.delete(articleSafe)
            }
        }
    }
}