package allankevyn.io.newsappstarter.ui

import android.webkit.WebViewClient
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import allankevyn.io.newsappstarter.R
import allankevyn.io.newsappstarter.databinding.ActivityArticleBinding
import allankevyn.io.newsappstarter.model.Article
import allankevyn.io.newsappstarter.model.data.NewsDataSource
import allankevyn.io.newsappstarter.presenter.ViewHome
import allankevyn.io.newsappstarter.presenter.favorite.FavoritePresenter


class ArticleActivity : AbstractActivity(), ViewHome.Favorite {

    private lateinit var article: Article
    private lateinit var presenter: FavoritePresenter
    private lateinit var binding: ActivityArticleBinding

    override fun getLayout(): ViewBinding {
        binding = ActivityArticleBinding.inflate(layoutInflater)
        return binding
    }

    override fun onInject() {
        getArticle()
        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this,dataSource)

        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { url ->
                loadUrl(url)
            }
        }

        binding.fav.setOnClickListener {
            presenter.saveArticle(article)
            Snackbar.make(
                it, R.string.article_saved_successful,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun getArticle() {
        intent.extras?.let {
            article = it.get("article") as Article
        }
    }

    override fun showArticles(articles: List<Article>) {

    }
}