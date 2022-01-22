package allankevyn.io.newsappstarter.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import allankevyn.io.newsappstarter.databinding.ItemNewsBinding
import allankevyn.io.newsappstarter.model.Article
import kotlinx.android.synthetic.main.item_news.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount(): Int = differ.currentList.size

    private fun String.getDateTimeFormatted(): String {
        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", getLocale())
            val date = dateFormat.parse(this)
            date?.let {
                return getDateToStringFormatted(date, "dd/MM/yyyy") + " - " + this.substring(11..18)
            }
        } catch (e: ParseException) {
            e.localizedMessage?.let {
                Log.d("TAG", "getDateTimeFormatted: $e")
            }
        }
        return orEmpty()
    }

    fun getDateToStringFormatted(date: Date, dateString: String): String {
        val simpleDateFormat = SimpleDateFormat(dateString, getLocale())
        return simpleDateFormat.format(date)
    }

    fun getLocale(): Locale {
        return Locale("pt", "BR")
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        with(holder){
            with(differ.currentList[position]){
                Glide.with(holder.itemView.context).load(urlToImage).into(binding.ivArticleImage)
                binding.tvTitle.text = author ?: source?.name
                binding.tvSource.text = source?.name ?: author
                binding.tvDescription.text = description
                binding.tvPublishedAt.text = publishedAt?.getDateTimeFormatted()

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let { click ->
                        click(this)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnClickListener(listener: (Article) -> Unit){
        onItemClickListener = listener
    }
}