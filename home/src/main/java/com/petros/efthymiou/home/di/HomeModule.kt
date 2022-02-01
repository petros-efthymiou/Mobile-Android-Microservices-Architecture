/*
 *
 * Copyright (C) 2022 Petros Efthymiou Open Source Project
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.petros.efthymiou.home.di

import com.jakewharton.espresso.OkHttp3IdlingResource
import com.petros.efthymiou.data.*
import com.petros.efthymiou.data.combined.GetArticlesSourceCombined
import com.petros.efthymiou.data.combined.LikeArticleSourceCombined
import com.petros.efthymiou.data.local.ArticleDataSourceLocal
import com.petros.efthymiou.data.local.AuthorDataSourceLocal
import com.petros.efthymiou.data.local.LikesDataSourceLocal
import com.petros.efthymiou.data.remote.ArticleDataSourceRemote
import com.petros.efthymiou.domain.entities.mappers.ArticlesMapper
import com.petros.efthymiou.domain.usecases.*
import com.petros.efthymiou.domain.usecases.datasources.GetArticlesSource
import com.petros.efthymiou.domain.usecases.datasources.LikeArticleSource
import com.petros.efthymiou.home.local.database.*
import com.petros.efthymiou.presentation.ArticlesPresentationMapper
import com.petros.efthymiou.presentation.HomeStateMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.petros.efthymiou.home.local.files.AuthorLocalDataGateway
import com.petros.efthymiou.home.remote.ArticlesApi
import com.petros.efthymiou.home.remote.ArticlesRemoteDataGateway
import com.petros.efthymiou.home.remote.ArticlesService
import com.petros.efthymiou.home.HomeViewModel
import com.petros.efthymiou.home.local.files.AuthorsMapperPlain
import com.petros.efthymiou.home.local.files.LikesLocalDataGateway
import com.petros.efthymiou.home.remote.ArticlesMapperDb
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val homeModule = module {

    single { ArticlesMapper() }
    single { GetArticles(get(), get()) }
    single { ArticlesPresentationMapper() }
    single { ArticlesMapperPlain() }
    single { LikeArticle(get(), get()) }
    single { HomeStateMapper(get()) }
    single<GetArticlesSource> { GetArticlesSourceCombined(get(), get(), get(), get(), get()) }
    single<ArticleDataSourceRemote> { ArticlesRemoteDataGateway(get(), get(), get(), get()) }
    single<ArticleDataSourceLocal> { ArticlesLocalDataGateway(get(), get()) }
    single { AuthorsMapperPlain() }
    single<AuthorDataSourceLocal> { AuthorLocalDataGateway(get()) }
    viewModel { HomeViewModel(get(), get(), get()) }
    single { ArticlesService(get()) }
    single { ArticlesMapperDb() }
    single<LikesDataSourceLocal> { LikesLocalDataGateway() }
    single { ArticlesAuthorsLikesMapper() }
    single<LikeArticleSource> { LikeArticleSourceCombined(get(), get(), get(), get(), get()) }
    single { provideArticlesApi(get()) }
    single { provideRetrofit(client) }
    single { AppDatabase.getInstance(androidApplication()) }
    single { provideArticlesDao(get()) }


}

fun interceptor() : HttpLoggingInterceptor{
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return interceptor
}

val client = OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS).connectTimeout(20, TimeUnit.SECONDS)
    .addInterceptor(interceptor()).build()

val idlingResource = OkHttp3IdlingResource.create("okhttp", client)

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(API_HOST)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()


fun provideArticlesApi(retrofit: Retrofit): ArticlesApi = retrofit.create(ArticlesApi::class.java)

fun provideArticlesDao(appDatabase: AppDatabase): ArticlesDao {
    return appDatabase.articlesDao()
}

const val API_HOST = "https://articles-clean.herokuapp.com/api/v1/"

