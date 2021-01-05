package tech.developingdeveloper.datapersistenceroomexample.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tech.developingdeveloper.datapersistenceroomexample.data.model.Book


/**
 * @author Abhishek Saxena
 * @since 03-01-2021 11:05
 */

@Database(
    entities = [Book::class],
    version = 1,
    exportSchema = false
)
abstract class BookDatabase : RoomDatabase() {

    abstract fun getBookDao(): BookDao

    companion object {

        private const val DB_NAME = "book_db"

        private var INSTANCE: BookDatabase? = null

        fun getInstance(context: Context): BookDatabase {
            if (INSTANCE != null)
                return INSTANCE!!

            val instance = Room.databaseBuilder(
                context,
                BookDatabase::class.java,
                DB_NAME
            )
                .fallbackToDestructiveMigration()
                .build()

            INSTANCE = instance
            return instance

        }
    }

}