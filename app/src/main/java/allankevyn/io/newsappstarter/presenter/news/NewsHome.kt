package allankevyn.io.newsappstarter.presenter.news

import allankevyn.io.newsappstarter.model.NewsResponse

interface NewsHome {

    interface Presenter{
        fun requestAll()
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(Message: String)
        fun onComplete()
    }
}