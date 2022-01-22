package allankevyn.io.newsappstarter.presenter.favorite

import allankevyn.io.newsappstarter.model.Article
import allankevyn.io.newsappstarter.model.data.NewsDataSource
import allankevyn.io.newsappstarter.presenter.ViewHome

class FavoritePresenter(val view: ViewHome.Favorite,
    private val dataSource: NewsDataSource): FavoriteHome.Presenter {

    fun getAll(){
        this.dataSource.getAllArticle(this)
    }

    fun saveArticle(article: Article){
        this.dataSource.saveArticle(article)
    }

    fun deleteArticle(article: Article){
        this.dataSource.deleteArticle(article)
    }

    override fun onSuccess(articles: List<Article>) {
        this.view.showArticles(articles)
    }
}