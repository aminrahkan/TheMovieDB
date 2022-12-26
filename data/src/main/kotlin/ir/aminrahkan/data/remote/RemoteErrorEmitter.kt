package ir.aminrahkan.data.api


// Developer : Amin Rahkan - Amin.Rahkan7@gmail.com  
// Date : 12/2/22 - 2022
// Project name : TheMovieDB


interface RemoteErrorEmitter {
    fun onError(msg: String)
    fun onError(errorType: ErrorType)
}