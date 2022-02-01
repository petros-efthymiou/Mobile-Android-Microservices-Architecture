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
import com.petros.efthymiou.domain.entities.plain.ArticleCategory
import java.text.SimpleDateFormat


class ArticlesPresentationMapper : Function1<List<Article>, List<ArticlePresentation>> {

    override fun invoke(
        articles: List<Article>,
    ): List<ArticlePresentation> =
        articles.map {
            mapArticle(it)
        }

    private fun mapArticle(article: Article): ArticlePresentation =
        ArticlePresentation(
            id = article.id,
            title = article.title,
            desc = article.desc,
            date = mapDate(article.date),
            readTime = article.readTime,
            category = mapCategory(article.category),
            imageUrl = article.imageUrl,
            likes = article.likes,
            authorName = article.authorName,
            canLike = article.canLike,
            likeActionAlpha = mapLikeAlpha(article.canLike)
        )

    private fun mapLikeAlpha(canLike: Boolean): Float {
        return when(canLike) {
            true -> 1F
            false -> 0.2F
        }
    }

    @Suppress("SimpleDateFormat")
    private fun mapDate(date: String): String =
        SimpleDateFormat("dd-MM-yyyy").format(SimpleDateFormat("yyyy-MM-dd").parse(date))


    private fun mapCategory(category: ArticleCategory): String = when (category) {
        ArticleCategory.SPORTS -> "Sports"
        ArticleCategory.MUSIC -> "Music"
        ArticleCategory.POLITICS -> "Politics"
        ArticleCategory.OTHER -> "Other"
    }
}