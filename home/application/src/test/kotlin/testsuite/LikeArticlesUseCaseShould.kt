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

package testsuite

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.petros.efthymiou.domain.entities.Article
import com.petros.efthymiou.domain.entities.mappers.ArticlesMapper
import com.petros.efthymiou.domain.entities.plain.ArticlePlain
import com.petros.efthymiou.domain.entities.plain.ArticlesAuthorsLikes
import com.petros.efthymiou.domain.entities.plain.AuthorPlain
import com.petros.efthymiou.domain.usecases.LikeArticle
import com.petros.efthymiou.domain.usecases.datasources.LikeArticleSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import utils.BaseUnitTest

@ExperimentalCoroutinesApi
class LikeArticlesUseCaseShould : BaseUnitTest() {

    lateinit var likeArticle: LikeArticle

    private val source: LikeArticleSource = mock()
    private val mapper: ArticlesMapper = mock()
    private val articlesData: ArticlesAuthorsLikes = mockArticleData()


    private val article: Article = mock()
    private val likedArticle: Article = mock()
    private val likedArticlePlain: ArticlePlain = mock()
    private val articleId = "id"

    @Test
    fun savesLikedArticle() = runTest {
        happyPath()

        likeArticle(articleId)

        verify(source, times(1)).updateArticle(likedArticlePlain)
    }

    @Test
    fun doesNotSaveArticleInCaseOfError() = runTest {
        errorCase()

        likeArticle(articleId)

        verify(source, times(0)).updateArticle(likedArticlePlain)
    }

    private suspend fun errorCase() {
        whenever(source.findArticle(articleId)).thenReturn(articlesData)

        val articlePlain = articlesData.articlesPlain[0]

        whenever(
            mapper.mapArticle(
                articlePlain,
                articlesData.authorsPlain,
                articlesData.likedArticlesIds
            )
        ).thenReturn(article)

        whenever(article.likeArticle()).thenReturn(likedArticle)

        whenever(mapper.invoke(likedArticle, articlePlain.authorId, articlePlain.words)).thenReturn(null)

        likeArticle = LikeArticle(source, mapper)
    }

    private suspend fun happyPath() {
        whenever(source.findArticle(articleId)).thenReturn(articlesData)

        val articlePlain = articlesData.articlesPlain[0]

        whenever(
            mapper.mapArticle(
                articlePlain,
                articlesData.authorsPlain,
                articlesData.likedArticlesIds
            )
        ).thenReturn(article)

        whenever(article.likeArticle()).thenReturn(likedArticle)

        whenever(mapper.invoke(likedArticle, articlePlain.authorId, articlePlain.words)).thenReturn(
            likedArticlePlain
        )

        likeArticle = LikeArticle(source, mapper)
    }


    private fun mockArticleData() = ArticlesAuthorsLikes(
        listOf(
            ArticlePlain(
                "id",
                "title",
                "desc",
                "date",
                2000,
                "sports",
                "imageUrl",
                200,
                "auhorId1"
            )
        ),
        listOf(
            AuthorPlain(
                "authorId1",
                "Petros Efthymiou",
                200
            )
        ),
        listOf(
            "id"
        )
    )
}