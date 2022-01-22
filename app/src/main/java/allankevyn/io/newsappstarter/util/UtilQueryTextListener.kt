package allankevyn.io.newsappstarter.util

import android.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.coroutineScope
import allankevyn.io.newsappstarter.util.Constants.Companion.SEARCH_NEWS_DELAY
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class UtilQueryTextListener(
    lifecycle: Lifecycle,
    private val utilQueryTextListener: (String?) -> Unit
): SearchView.OnQueryTextListener, LifecycleObserver {

    private val coroutineScope = lifecycle.coroutineScope
    private var searchJob: Job? = null

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            newText?.let {
                delay(SEARCH_NEWS_DELAY) //delay de quando pesquisamos
                utilQueryTextListener(newText)
            }
        }
        return false
    }
}