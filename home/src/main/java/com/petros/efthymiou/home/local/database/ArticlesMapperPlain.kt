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

package com.petros.efthymiou.home.local.database

import com.petros.efthymiou.domain.entities.plain.ArticlePlain

class ArticlesMapperPlain : Function1<List<ArticleDb>, List<ArticlePlain>> {

    override fun invoke(
        articlesDb: List<ArticleDb>,
    ): List<ArticlePlain> =
        articlesDb.map {
            mapToPlain(it)
        }

    fun mapToPlain(article: ArticleDb): ArticlePlain =
        ArticlePlain(
            id = article.id,
            title = article.title,
            desc = article.desc,
            date = article.date,
            category = article.category,
            imageUrl = article.imageUrl,
            likes = article.likes,
            authorId = article.authorId,
            words = article.words
        )

    fun mapToDb(articlesPlain: List<ArticlePlain>): List<ArticleDb> =
        articlesPlain.map {
            mapToDb(it)
        }

    fun mapToDb(plain: ArticlePlain): ArticleDb =
        ArticleDb(
            id = plain.id,
            title = plain.title,
            desc = plain.desc,
            date = plain.date,
            category = plain.category,
            imageUrl = plain.imageUrl,
            likes = plain.likes,
            authorId = plain.authorId,
            words = plain.words,
        )
}