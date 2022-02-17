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

import com.petros.efthymiou.home.local.database.ArticleDb

class ArticlesMapperDb : Function1<List<ArticleRaw>, List<ArticleDb>> {

    override fun invoke(
        articlesRaw: List<ArticleRaw>,
    ): List<ArticleDb> =
        articlesRaw.map {
            mapToDb(it)
        }

    private fun mapToDb(raw: ArticleRaw): ArticleDb =
        ArticleDb(
            id = raw.id,
            title = raw.title,
            desc = raw.desc,
            date = raw.date,
            category = raw.category,
            imageUrl = raw.imageUrl,
            likes = raw.stars,
            authorId = raw.authorId,
            words = raw.words
        )

    fun mapToRaw(db: ArticleDb): ArticleRaw =
        ArticleRaw(
            id = db.id,
            title = db.title,
            desc = db.desc,
            date = db.date,
            category = db.category,
            imageUrl = db.imageUrl,
            stars = db.likes,
            authorId = db.authorId,
            words = db.words,
            otherJunkTheBackendIsSending = null
        )
}