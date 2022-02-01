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

package com.petros.efthymiou.home.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.*

class ArticlesService(private val api: ArticlesApi) {

    suspend fun fetchArticles(): Flow<List<ArticleRaw>> {
        return flow {
            emit(api.fetchAllPlaylists())
        }
    }

    suspend fun updateArticle(articleRaw: ArticleRaw) {
        //add return type if action is required on completion or error
        kotlin.runCatching {
            api.updateArticle(articleRaw.id, articleRaw)
        }
    }
}

interface ArticlesApi {

    @GET("articles")
    suspend fun fetchAllPlaylists(): List<ArticleRaw>

    @PUT("articles/{id}")
    suspend fun updateArticle(@Path("id") id: String, @Body article: ArticleRaw)
}


