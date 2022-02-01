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

package com.petros.efthymiou.presentation

import com.petros.efthymiou.domain.entities.Article

class HomeStateMapper constructor(private val mapper: ArticlesPresentationMapper) :
    Function1<Result<List<Article>>, HomeState> {

    override fun invoke(articles: Result<List<Article>>): HomeState {
        if (articles.isSuccess) {
            return when (articles.getOrNull().isNullOrEmpty()) {
                true -> HomeState.Empty
                false -> HomeState.Success(mapper(articles.getOrThrow()))
            }
        }
        return HomeState.Error
    }
}
