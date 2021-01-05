package tech.developingdeveloper.datapersistenceroomexample.utils

import java.lang.Exception


/**
 * @author Abhishek Saxena
 * @since 03-01-2021 12:57
 */

sealed class Result<out R> {
    class Success<out T>(val data: T): Result<T>()
    class Failure(val exception: Exception): Result<Nothing>()
    object Loading: Result<Nothing>()
}
