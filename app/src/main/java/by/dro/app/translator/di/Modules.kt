package by.dro.app.translator.di

import android.util.Log
import by.dro.app.translator.api.ApiService
import by.dro.app.translator.repository.ApiRepository
import by.dro.app.translator.ui.meaning.MeaningViewModel
import by.dro.app.translator.ui.word.search.SearchWordViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"

    factory<Interceptor> {
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Log.d("API", it) })
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }

    factory { OkHttpClient.Builder().addInterceptor(get()).build() }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    factory{ get<Retrofit>().create(ApiService::class.java) }
}


val repositoryModule = module {
    factory { ApiRepository(get()) }
}

val viewModelModule = module {
    viewModel { SearchWordViewModel(get()) }

    viewModel { MeaningViewModel(get()) }
}