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

package com.petros.efthymiou.data

import com.petros.efthymiou.domain.entities.plain.ArticlesAuthorsLikes
import com.petros.efthymiou.domain.entities.plain.ArticlePlain
import com.petros.efthymiou.domain.entities.plain.AuthorPlain

class ArticlesAuthorsLikesMapper : Function3<List<ArticlePlain>,
        List<AuthorPlain>,
        List<String>,
        ArticlesAuthorsLikes> {

    override operator fun invoke(
        articlesPlain: List<ArticlePlain>,
        authorsPlain: List<AuthorPlain>,
        likedArticlesIds: List<String>
    ): ArticlesAuthorsLikes =
        ArticlesAuthorsLikes(
            articlesPlain,
            authorsPlain,
            likedArticlesIds
        )
}