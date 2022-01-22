package allankevyn.io.newsappstarter.presenter.search

import allankevyn.io.newsappstarter.model.NewsResponse
import allankevyn.io.newsappstarter.model.data.NewsDataSource
import allankevyn.io.newsappstarter.presenter.ViewHome

class SearchPresenter(
    val view: ViewHome.View,
    private val dataSource: NewsDataSource
) : SearchHome.Presenter {

    override fun search(term: String) {
        this.view.showProgressBar()
        this.dataSource.searchNews(term, this)
    }

    override fun onSuccess(newsResponse: NewsResponse) {
        this.view.showArticles(newsResponse.articles)
    }

    override fun onError(Message: String) {
        this.view.showFailure(Message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }
}