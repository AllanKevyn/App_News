package allankevyn.io.newsappstarter.presenter.news

import allankevyn.io.newsappstarter.model.NewsResponse
import allankevyn.io.newsappstarter.model.data.NewsDataSource
import allankevyn.io.newsappstarter.presenter.ViewHome

class NewsPresenter(
    val view: ViewHome.View,
    private val dataSource: NewsDataSource
): NewsHome.Presenter {
    override fun requestAll() {
        this.view.showProgressBar()
        this.dataSource.getBreakingNews(this)
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