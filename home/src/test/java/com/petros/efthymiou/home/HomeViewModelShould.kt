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

package com.petros.efthymiou.home

import com.nhaarman.mockitokotlin2.*
import com.petros.efthymiou.domain.entities.Article
import com.petros.efthymiou.domain.usecases.GetArticles
import com.petros.efthymiou.domain.usecases.LikeArticle
import com.petros.efthymiou.presentation.ArticlePresentation
import com.petros.efthymiou.presentation.HomeIntent
import com.petros.efthymiou.presentation.HomeState
import com.petros.efthymiou.presentation.HomeStateMapper
import com.petros.efthymiou.utils.BaseUnitTest
import com.petros.efthymiou.utils.articlePlain1
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelShould : BaseUnitTest() {

    private lateinit var sut: HomeViewModel

    private val stateMapper: HomeStateMapper = mock()
    private val getArticles: GetArticles = mock()
    private val likeArticle: LikeArticle = mock()
    private val articles: List<Article> = mock()
    private val articlesPresentation: List<ArticlePresentation> = mock()
    private val articlesInput = Result.success(articles)
    private val successfulState = HomeState.Success(articlesPresentation)
    private val loadingState = HomeState.Loading
    private val articleId = articlePlain1.id

    @Test
    fun turnsLoaderInitially() = runTest {
        happyPath()

        assertEquals(loadingState, sut.uiState.value)
    }

    @Test
    fun delegatesLikeArticle() = runTest {
        happyPath()

        sut.handleIntent(HomeIntent.LikeArticle(articleId))

        verify(likeArticle, times(1)).invoke(articleId)
    }

    private fun happyPath() = runTest {
        whenever(getArticles()).thenReturn(flow {
            emit(articlesInput)
        })
        whenever(stateMapper(articlesInput)).thenReturn(successfulState)

        sut = HomeViewModel(stateMapper, getArticles, likeArticle)
    }

    @Test
    fun delegatesViewArticlesIntent() = runTest {
        happyPath()

        sut.handleIntent(HomeIntent.ViewAllArticles)

        delay(50)

        verify(getArticles, times(1)).invoke()
    }
}