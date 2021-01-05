package tech.developingdeveloper.datapersistenceroomexample.data.local

import android.app.Application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import tech.developingdeveloper.datapersistenceroomexample.data.model.Book
import tech.developingdeveloper.datapersistenceroomexample.utils.Result


/**
 * @author Abhishek Saxena
 * @since 03-01-2021 12:33
 */

class BookRepository(application: Application) {

    // not a good practice, use dependency injection
    private val bookDatabase = BookDatabase.getInstance(application)

    fun observeBooks() = bookDatabase.getBookDao().observeBooks()

    fun insertBook(book: Book) = flow {
        val id = bookDatabase.getBookDao().insertBook(book)
        emit(book.copy(id = id))
    }

    fun updateBook(book: Book) = flow {
        bookDatabase.getBookDao().updateBook(book)
        emit(book)
    }

    fun deleteBook(book: Book) = flow {
        bookDatabase.getBookDao().deleteBook(book)
        emit(book.id)
    }
}