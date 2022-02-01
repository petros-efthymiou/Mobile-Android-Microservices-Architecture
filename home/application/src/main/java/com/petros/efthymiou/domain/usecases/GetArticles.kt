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

package com.petros.efthymiou.domain.usecases

import com.petros.efthymiou.domain.entities.Article
import com.petros.efthymiou.domain.entities.mappers.ArticlesMapper
import com.petros.efthymiou.domain.usecases.datasources.GetArticlesSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.lang.RuntimeException


class GetArticles constructor(
    private val source: GetArticlesSource,
    private val mapper: ArticlesMapper
) {

    suspend operator fun invoke(): Flow<Result<List<Article>>> =
        source.articles().map {
            Result.success(mapper(it))
        }.catch {
            emit(Result.failure(RuntimeException(DATA_PROCESS_ERROR)))
        }
}

const val DATA_PROCESS_ERROR = "Failed to process data"