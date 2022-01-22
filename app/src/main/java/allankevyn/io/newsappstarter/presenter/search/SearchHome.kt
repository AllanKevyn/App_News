package allankevyn.io.newsappstarter.presenter.search

import allankevyn.io.newsappstarter.model.NewsResponse

interface SearchHome {

    interface Presenter{
        fun search(term: String)
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(Message: String)
        fun onComplete()
    }
}