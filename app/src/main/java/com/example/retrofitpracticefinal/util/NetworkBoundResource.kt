package com.example.retrofitpracticefinal.util
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType>networkBoundResource(
    //get data from Room
   crossinline query: () -> Flow<ResultType>,
    //get data from REST API via Retrofit
   crossinline fetch: suspend () -> RequestType,
    //saved data fetched from Rest API to room
   crossinline savedFetchedData: suspend(RequestType) -> Unit,
   crossinline shouldFetch: (ResultType) -> Boolean = {true}
) = flow {
    val data = query().first()

   val flow = if(shouldFetch(data)) {
        emit(Resource.Loading(data))
        try {
            savedFetchedData(fetch())
            query().map { Resource.Success(it)}
        }catch(throwable: Throwable) {
            query().map {Resource.Error(throwable, it)}
        }
    } else {
        query().map { Resource.Success(it)}
    }
    emitAll(flow)
}