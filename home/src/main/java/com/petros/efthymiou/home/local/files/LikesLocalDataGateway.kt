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

package com.petros.efthymiou.home.local.files

import com.petros.efthymiou.data.local.LikesDataSourceLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class LikesLocalDataGateway : LikesDataSourceLocal {

    private var likedArticlesIds = mutableListOf<String>()
    private val flow = MutableStateFlow(likedArticlesIds)

    override suspend fun findLikedArticles(): Flow<List<String>> = flow

    override suspend fun saveLikedArticle(id: String)  {
        likedArticlesIds.add(id)
        flow.emit(likedArticlesIds)
    }
}