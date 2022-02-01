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

import android.util.Log
import com.petros.efthymiou.data.remote.ArticleDataSourceRemote
import com.petros.efthymiou.domain.entities.plain.ArticlePlain
import kotlinx.coroutines.flow.*
import com.petros.efthymiou.home.local.database.ArticlesDao
import com.petros.efthymiou.home.local.database.ArticlesMapperPlain

class ArticlesRemoteDataGateway(
    private val service: ArticlesService,
    private val dao: ArticlesDao,
    private val mapperDB: ArticlesMapperDb,
    private val mapperPlain: ArticlesMapperPlain
) : ArticleDataSourceRemote {

    override suspend fun fetchArticles(): Flow<List<ArticlePlain>> =
        service.fetchArticles().map(mapperDB).onEach {
            dao.insert(it)
            //Below we map to plain for the case that we want to retrieve data directly from server instead of using db
        }.map(mapperPlain).catch {
            //add extra error handling if required
            Log.e("Networking", it.toString())
        }

    override suspend fun updateArticleToServer(plain: ArticlePlain) =
        service.updateArticle(mapperDB.mapToRaw(mapperPlain.mapToDb(plain)))
}