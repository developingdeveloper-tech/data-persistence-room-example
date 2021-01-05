package tech.developingdeveloper.datapersistenceroomexample.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tech.developingdeveloper.datapersistenceroomexample.data.model.Book
import tech.developingdeveloper.datapersistenceroomexample.databinding.BooksListItemBinding


/**
 * @author Abhishek Saxena
 * @since 23-12-2020 04:05
 */

class BooksAdapter(private val onClickListener: (book: Book) -> Unit) :
    ListAdapter<Book, BooksAdapter.ViewHolder>(BookDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClickListener)

    }


    class ViewHolder private constructor(private val binding: BooksListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = BooksListItemBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(book: Book, onClickListener: (book: Book) -> Unit) {
            binding.book = book
            binding.executePendingBindings()

            binding.root.setOnClickListener {

                onClickListener(book)
            }
        }
    }

    class BookDiffUtils : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }

}