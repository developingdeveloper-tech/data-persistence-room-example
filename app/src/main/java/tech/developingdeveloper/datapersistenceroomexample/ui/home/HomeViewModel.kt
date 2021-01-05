package tech.developingdeveloper.datapersistenceroomexample.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import tech.developingdeveloper.datapersistenceroomexample.data.local.BookRepository
import tech.developingdeveloper.datapersistenceroomexample.data.model.Book
import tech.developingdeveloper.datapersistenceroomexample.utils.commonFlow
import tech.developingdeveloper.datapersistenceroomexample.utils.flowInIO

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    var id = 0L
    val title = MutableLiveData<String>()
    val author = MutableLiveData<String>()
    val pages = MutableLiveData<Int>()

    // not a good practice, use dependency injection
    // dependency injection is covered in future courses
    private val bookRepository = BookRepository(application)

    val books: LiveData<List<Book>> = bookRepository.observeBooks()
            .commonFlow()
            .asLiveData(Dispatchers.IO)

    fun onInsertBook() {

        if (!title.value.isNullOrBlank()
                && !author.value.isNullOrBlank()
                && pages.value in 1..1999) {
            val book = Book(title.value!!, author.value!!, pages.value!!)
            insertBook(book)
        }

    }

    private fun insertBook(book: Book) {
        bookRepository.insertBook(book)
                .commonFlow { resetValues() }
                .flowInIO(viewModelScope)
    }

    fun onUpdateBook() {
        if (!title.value.isNullOrBlank()
                && !author.value.isNullOrBlank()
                && pages.value in 1..1999) {
            val book = Book(title.value!!, author.value!!, pages.value!!, id)
            updateBook(book)
        }

    }

    private fun updateBook(book: Book) {
        bookRepository.updateBook(book)
                .commonFlow { resetValues() }
                .flowInIO(viewModelScope)
    }

    fun onDeleteBook() {
        if (!title.value.isNullOrBlank()
                && !author.value.isNullOrBlank()
                && pages.value in 1..1999) {
            val book = Book(title.value!!, author.value!!, pages.value!!, id)
            deleteBook(book)
        }
    }

    private fun deleteBook(book: Book) {
        bookRepository.deleteBook(book)
                .commonFlow { resetValues() }
                .flowInIO(viewModelScope)
    }

    fun resetValues() {
        id = 0
        title.postValue(null)
        author.postValue(null)
        pages.postValue(null)
    }

}
