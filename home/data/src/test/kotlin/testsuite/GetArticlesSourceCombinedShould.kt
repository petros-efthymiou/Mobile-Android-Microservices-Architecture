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
import com.nhaarman.mockitokotlin2.whenever
import com.petros.efthymiou.data.ArticlesAuthorsLikesMapper
import com.petros.efthymiou.data.combined.GetArticlesSourceCombined
import com.petros.efthymiou.data.local.ArticleDataSourceLocal
import com.petros.efthymiou.data.local.AuthorDataSourceLocal
import com.petros.efthymiou.data.local.LikesDataSourceLocal
import com.petros.efthymiou.data.remote.ArticleDataSourceRemote
import com.petros.efthymiou.domain.entities.plain.ArticlePlain
import com.petros.efthymiou.domain.entities.plain.ArticlesAuthorsLikes
import com.petros.efthymiou.domain.entities.plain.AuthorPlain
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import utils.BaseUnitTest
import utils.likedIds

@ExperimentalCoroutinesApi
class GetArticlesSourceCombinedShould : BaseUnitTest() {

    private lateinit var sut : GetArticlesSourceCombined

    private val articleLocalSource: ArticleDataSourceLocal = mock()
    private val articleRemoteSource: ArticleDataSourceRemote = mock()
    private val authorLocalSource: AuthorDataSourceLocal = mock()
    private val likesLocalSource: LikesDataSourceLocal = mock()
    private val mapper: ArticlesAuthorsLikesMapper = mock()
    private val articlesPlain: List<ArticlePlain> = mock()
    private val authorsPlain: List<AuthorPlain> = mock()
    private val expected : ArticlesAuthorsLikes = mock()


    @Test
    fun combineInformationForGetArticlesUseCase() = runTest {
        happyPath()

        val actual = sut.articles().first()

        assertEquals(expected, actual)
    }

    @Test
    fun combineArticlesInformationFromLocalAndRemote() = runTest {
        happyPath()

        val actual = sut.articles().count()

        assertEquals(2, actual)
    }

    private suspend fun happyPath() {
        whenever(articleLocalSource.streamAllArticles()).thenReturn(flow {
            emit(articlesPlain)
        })

        whenever(articleRemoteSource.fetchArticles()).thenReturn(flow {
            emit(articlesPlain)
        })

        whenever(authorLocalSource.findAuthors()).thenReturn(flow {
            emit(authorsPlain)
        })

        whenever(likesLocalSource.findLikedArticles()).thenReturn(flow {
            emit(likedIds)
        })

        whenever(mapper(articlesPlain, authorsPlain, likedIds)).thenReturn(expected)

        sut = GetArticlesSourceCombined(
            articleLocalSource,
            articleRemoteSource,
            authorLocalSource,
            likesLocalSource,
            mapper
        )
    }
}