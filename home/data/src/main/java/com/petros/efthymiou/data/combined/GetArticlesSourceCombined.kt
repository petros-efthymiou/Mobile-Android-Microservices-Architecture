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

package com.petros.efthymiou.data.combined

import com.petros.efthymiou.data.ArticlesAuthorsLikesMapper
import com.petros.efthymiou.data.local.ArticleDataSourceLocal
import com.petros.efthymiou.data.local.AuthorDataSourceLocal
import com.petros.efthymiou.data.local.LikesDataSourceLocal
import com.petros.efthymiou.data.remote.ArticleDataSourceRemote
import com.petros.efthymiou.domain.entities.plain.ArticlesAuthorsLikes
import com.petros.efthymiou.domain.usecases.DATA_PROCESS_ERROR
import com.petros.efthymiou.domain.usecases.datasources.GetArticlesSource
import kotlinx.coroutines.flow.*
import java.lang.RuntimeException

class GetArticlesSourceCombined constructor(
    private val articleLocalSource: ArticleDataSourceLocal,
    private val articleRemoteSource: ArticleDataSourceRemote,
    private val authorLocalSource: AuthorDataSourceLocal,
    private val likesLocalSource: LikesDataSourceLocal,
    private val mapper: ArticlesAuthorsLikesMapper
) : GetArticlesSource {

    override suspend fun articles(): Flow<ArticlesAuthorsLikes> {
        val articlesSource = merge(articleLocalSource.streamAllArticles(), articleRemoteSource.fetchArticles())
        val authorsSource = authorLocalSource.findAuthors()
        val likedArticlesIdsSource = likesLocalSource.findLikedArticles()

        return combine(articlesSource, authorsSource, likedArticlesIdsSource) {
                articles, authors, likedArticlesIds ->
                    mapper(articles, authors, likedArticlesIds)
        }
    }
}