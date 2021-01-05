package tech.developingdeveloper.datapersistenceroomexample.utils

import tech.developingdeveloper.datapersistenceroomexample.data.model.Book


/**
 * @author Abhishek Saxena
 * @since 03-01-2021 12:56
 */

object BookValidation {

    private val wordCapitalRegex = "^(?=.{0,30}$)([A-Z][a-z]+)( [A-Z][a-z]+)*$".toRegex()

    fun validateBook(book: Book): Result<*> = when {
        !hasCapitalWords(book.title) -> Result.Failure(Exception("Invalid book title"))
        !hasCapitalWords(book.author) -> Result.Failure(Exception("Invalid book author"))
        !isValidPages(book.pages) -> Result.Failure(Exception("Pages must be between 1 and 2000"))
        else -> Result.Success(Unit)
    }

    private fun hasCapitalWords(input: String): Boolean {
        return input.matches(wordCapitalRegex)
    }

    private fun isValidPages(pageNumber: Int) =
        pageNumber in 1..1999


}