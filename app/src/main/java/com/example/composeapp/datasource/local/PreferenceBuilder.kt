package com.example.composeapp.datasource.local

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class PreferenceBuilder @Inject constructor(
    isDebug:Boolean,
    context: Context,
    name: String
) {
    private val masterKey: MasterKey by lazy {
        MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    }

     val sharedPreferences: SharedPreferences by lazy {
        if(isDebug)
        context.getSharedPreferences(name, Context.MODE_PRIVATE)
    else
            EncryptedSharedPreferences.create(
                context,
                name,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
    }
     val editor: SharedPreferences.Editor by lazy { sharedPreferences.edit() }


}