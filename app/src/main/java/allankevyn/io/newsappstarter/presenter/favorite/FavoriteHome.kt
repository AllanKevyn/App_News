package allankevyn.io.newsappstarter.presenter.favorite

import allankevyn.io.newsappstarter.model.Article

interface FavoriteHome {

    interface Presenter{
        fun onSuccess(articles : List<Article>)
    }
}