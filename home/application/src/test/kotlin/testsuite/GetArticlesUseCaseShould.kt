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
import com.petros.efthymiou.domain.entities.Article
import com.petros.efthymiou.domain.entities.mappers.ArticlesMapper
import com.petros.efthymiou.domain.entities.plain.ArticlesAuthorsLikes
import com.petros.efthymiou.domain.usecases.DATA_PROCESS_ERROR
import com.petros.efthymiou.domain.usecases.GetArticles
import com.petros.efthymiou.domain.usecases.datasources.GetArticlesSource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test
import utils.BaseUnitTest
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class GetArticlesUseCaseShould : BaseUnitTest() {

    lateinit var sut: GetArticles

    private val source: GetArticlesSource = mock()
    private val mapper: ArticlesMapper = mock()
    private val articlesData: ArticlesAuthorsLikes = mock()
    private val articles: List<Article> = mock()
    private val expected = Result.success(articles)

    @Test
    fun emitSuccessfulArticlesResult() = runTest {
        happyPath()

        val actual = sut().first()

        assertEquals(expected, actual)
    }

    @Test
    fun emitsErrorWhenFailToMapArticles() = runTest {
        errorCase()

        val result = sut().single().exceptionOrNull()

        assertEquals(DATA_PROCESS_ERROR, result?.message)
    }

    private suspend fun errorCase() {
        whenever(source.articles()).thenReturn(
            flow {
                emit(articlesData)
            }
        )

        whenever(mapper(articlesData)).thenThrow(RuntimeException())

        sut = GetArticles(source, mapper)
    }

    private suspend fun happyPath() {
        whenever(source.articles()).thenReturn(
            flow {
                emit(articlesData)
            }
        )

        whenever(mapper(articlesData)).thenReturn(articles)

        sut = GetArticles(source, mapper)
    }
}