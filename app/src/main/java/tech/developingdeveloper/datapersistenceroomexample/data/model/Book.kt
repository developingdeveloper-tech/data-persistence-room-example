package tech.developingdeveloper.datapersistenceroomexample.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Abhishek Saxena
 * @since 03-01-2021 10:59
 */

@Entity
data class Book(
    var title: String,
    var author: String,
    var pages: Int,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
) {
    constructor() : this("", "", 0)
}
