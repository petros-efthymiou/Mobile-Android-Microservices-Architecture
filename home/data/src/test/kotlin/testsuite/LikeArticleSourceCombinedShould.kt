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
import com.petros.efthymiou.data.ArticlesAuthorsLikesMapper
import com.petros.efthymiou.data.combined.LikeArticleSourceCombined
import com.petros.efthymiou.data.local.ArticleDataSourceLocal
import com.petros.efthymiou.data.local.AuthorDataSourceLocal
import com.petros.efthymiou.data.local.LikesDataSourceLocal
import com.petros.efthymiou.data.remote.ArticleDataSourceRemote
import com.petros.efthymiou.domain.entities.plain.ArticlePlain
import com.petros.efthymiou.domain.entities.plain.ArticlesAuthorsLikes
import com.petros.efthymiou.domain.entities.plain.AuthorPlain
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import utils.BaseUnitTest

@ExperimentalCoroutinesApi
class LikeArticleSourceCombinedShould : BaseUnitTest() {

    private lateinit var likeArticleSourceCombined : LikeArticleSourceCombined

    private val articleLocalSource: ArticleDataSourceLocal = mock()
    private val articleRemoteSource: ArticleDataSourceRemote = mock()
    private val authorLocalSource: AuthorDataSourceLocal = mock()
    private val likesLocalSource: LikesDataSourceLocal = mock()
    private val mapper: ArticlesAuthorsLikesMapper = mock()

    private val articlePlain = fakeArticlePlain()

    private val authorsPlain: List<AuthorPlain> = mock()
    private val likeIds = listOf("id1", "id2")
    private val articleId = "id1"
    private val expected : ArticlesAuthorsLikes = mock()


    @Test
    fun retrievesDataInfoAndCombinesThem() = runTest {
        happyPath()

        val actual = likeArticleSourceCombined.findArticle(articleId)

        assertEquals(expected, actual)
    }

    @Test
    fun retrievesDataInfoFromLocalDataSource() = runTest {
        happyPath()

        likeArticleSourceCombined.findArticle(articleId)

        verify(articleLocalSource, times(1)).findArticle(articleId)
    }

    @Test
    fun savesArticleInLocalDataSource() = runTest {
        makeLikeArticleSource()

        likeArticleSourceCombined.updateArticle(articlePlain)

        verify(articleLocalSource, times(1)).updateArticle(articlePlain)
    }

    @Test
    fun updatesArticleInRemoteSource() = runTest {
        makeLikeArticleSource()

        likeArticleSourceCombined.updateArticle(articlePlain)

        verify(articleRemoteSource, times(1)).updateArticleToServer(articlePlain)
    }

    @Test
    fun savesTheLikeLocally() = runTest {
        makeLikeArticleSource()

        likeArticleSourceCombined.updateArticle(articlePlain)

        verify(likesLocalSource, times(1)).saveLikedArticle(articleId)
    }


    private suspend fun happyPath() {
        whenever(articleLocalSource.findArticle(articleId)).thenReturn(articlePlain)

        whenever(authorLocalSource.findAuthors()).thenReturn(flow {
            emit(authorsPlain)
        })

        whenever(likesLocalSource.findLikedArticles()).thenReturn(flow {
            emit(likeIds)
        })

        whenever(mapper(listOf(articlePlain), authorsPlain, likeIds)).thenReturn(expected)

        makeLikeArticleSource()
    }

    private fun makeLikeArticleSource() {
        likeArticleSourceCombined = LikeArticleSourceCombined(
            articleLocalSource,
            articleRemoteSource,
            authorLocalSource,
            likesLocalSource,
            mapper
        )
    }

    private fun fakeArticlePlain() = ArticlePlain(
        "id1",
        "title",
        "desc",
        "date",
        2000,
        "sports",
        "imageUrl",
        200,
        "authorId1"
    )
}