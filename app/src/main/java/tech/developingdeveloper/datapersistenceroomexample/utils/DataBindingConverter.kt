package tech.developingdeveloper.datapersistenceroomexample.utils

import androidx.databinding.InverseMethod
import java.lang.NumberFormatException


/**
 * @author Abhishek Saxena
 * @since 03-01-2021 18:28
 */

object DataBindingConverter {

    @JvmStatic
    fun toInt(value: String?): Int? {
        return try {
            value?.toInt()
        } catch (numberFormatException: NumberFormatException) {
            null
        }
    }

    @JvmStatic
    @InverseMethod("toInt")
    fun toString(value: Int?): String =
            value?.toString() ?: ""
}
