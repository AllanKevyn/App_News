package allankevyn.io.newsappstarter.model.data

import allankevyn.io.newsappstarter.model.Article
import allankevyn.io.newsappstarter.model.db.ArticleDataBase

class NewsRepository(private val db: ArticleDataBase) {

    suspend fun updateInsert(article: Article) = db.getArticleDao().updateInsert(article)

    fun getAll(): List<Article> = db.getArticleDao().getAll()

    suspend fun delete(article: Article) = db.getArticleDao().delete(article)
}