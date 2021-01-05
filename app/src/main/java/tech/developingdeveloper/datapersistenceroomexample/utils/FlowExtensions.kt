package tech.developingdeveloper.datapersistenceroomexample.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


/**
 * @author Abhishek Saxena
 * @since 04-01-2021 14:35
 */


fun <T> Flow<T>.commonFlow(onCompletion: () -> Unit = { }): Flow<T> =
        onStart {
            // called when the flow begins
        }.catch {
            // called when any exception is thrown in the flow
        }.onCompletion {
            onCompletion()
        }

fun Flow<*>.flowInIO(viewModelScope: CoroutineScope, dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    flowOn(dispatcher)
            .launchIn(viewModelScope)
}