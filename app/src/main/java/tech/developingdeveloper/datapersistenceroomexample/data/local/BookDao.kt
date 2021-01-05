package tech.developingdeveloper.datapersistenceroomexample.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import tech.developingdeveloper.datapersistenceroomexample.data.model.Book


/**
 * @author Abhishek Saxena
 * @since 03-01-2021 11:02
 */

@Dao
interface BookDao {

    @Query("SELECT * FROM Book")
    fun observeBooks(): Flow<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book): Long

    @Update
    suspend fun updateBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book): Int
}