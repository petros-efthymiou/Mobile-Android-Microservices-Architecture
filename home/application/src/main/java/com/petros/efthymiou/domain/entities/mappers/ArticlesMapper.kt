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

package com.petros.efthymiou.domain.entities.mappers

import com.petros.efthymiou.domain.entities.*
import com.petros.efthymiou.domain.entities.plain.ArticleCategory
import com.petros.efthymiou.domain.entities.plain.ArticlePlain
import com.petros.efthymiou.domain.entities.plain.ArticlesAuthorsLikes
import com.petros.efthymiou.domain.entities.plain.AuthorPlain


class ArticlesMapper {

    operator fun invoke(
        data: ArticlesAuthorsLikes,
    ): List<Article> =
        data.articlesPlain.map { articlePlain ->
            mapArticle(articlePlain, data.authorsPlain, data.likedArticlesIds)
        }

    fun mapArticle(
        articlePlain: ArticlePlain,
        authorsPlain: List<AuthorPlain>,
        likedArticlesIds: List<String>
    ) = Article(
        id = articlePlain.id,
        title = articlePlain.title,
        desc = articlePlain.desc,
        date = articlePlain.date,
        readTime = mapReadTime(articlePlain.words),
        category = mapCategory(articlePlain.category),
        imageUrl = articlePlain.imageUrl,
        likes = articlePlain.likes,
        authorName = mapAuthorName(articlePlain.authorId, authorsPlain),
        canLike = mapCanLike(articlePlain.id, likedArticlesIds)
    )

    private fun mapCanLike(articleId: String, likedArticleIds: List<String>): Boolean =
        likedArticleIds.find {
            it == articleId
        }.isNullOrEmpty()

    private fun mapAuthorName(authorId: String, authors: List<AuthorPlain>): String =
        authors.find { author -> author.id == authorId }?.fullName ?: "-"

    private fun mapReadTime(words: Int) = words / 200

    private fun mapCategory(category: String): ArticleCategory {
        return when (category) {
            "sports" -> ArticleCategory.SPORTS
            "politics" -> ArticleCategory.POLITICS
            "music" -> ArticleCategory.MUSIC
            else -> ArticleCategory.OTHER
        }
    }


    operator fun invoke(domain: Article, authorId: String, words: Int): ArticlePlain =
        ArticlePlain(
            id = domain.id,
            title = domain.title,
            desc = domain.desc,
            date = domain.date,
            category = reverseMapCategory(domain.category),
            imageUrl = domain.imageUrl,
            likes = domain.likes,
            authorId = authorId,
            words = words,
        )

    private fun reverseMapCategory(category: ArticleCategory): String {
        return when (category) {
            ArticleCategory.SPORTS -> "sports"
            ArticleCategory.POLITICS -> "politics"
            ArticleCategory.MUSIC -> "music"
            else -> "other"
        }
    }

}