package com.example.composeapp.datasource.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class PreferenceController @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val builder: PreferenceBuilder
) {
    private val tag="PreferenceController"

  suspend  fun set(key: String, value: String)=withContext(dispatcher) {
      Timber.tag(tag).e("set key:$key value:$value")
      builder. editor.putString(key, value)
      builder. editor.apply()
    }

  suspend  fun remove(key: String) =withContext(dispatcher){
      Timber.tag(tag).e("remove key:$key")
      builder.editor.remove(key)
      builder.editor.apply()
    }

   suspend fun get(key: String): String? = withContext(dispatcher){
     val value :String? =  builder.sharedPreferences.getString(key, null)
       Timber.tag(tag).e("get key:$key value:$value")
       value
   }
}