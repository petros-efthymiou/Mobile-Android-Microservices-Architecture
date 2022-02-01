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
import com.petros.efthymiou.domain.entities.plain.ArticleCategory
import com.petros.efthymiou.presentation.ArticlePresentation
import com.petros.efthymiou.presentation.ArticlesPresentationMapper
import com.petros.efthymiou.presentation.HomeState
import com.petros.efthymiou.presentation.HomeStateMapper
import junit.framework.TestCase.assertEquals
import org.junit.Ignore
import org.junit.Test

class HomeStateMapperShould {

    private lateinit var mapper: HomeStateMapper
    private val articles = listOf(
        Article(
            "id1",
            "title",
            "desc",
            "2000-05-20",
            1200 / 200,
            ArticleCategory.SPORTS,
            "imageUrl",
            455,
            "Petros Efthymiou",
            false
        ),
        Article(
            "id2",
            "title2",
            "desc2",
            "2001-06-30",
            1300 / 200,
            ArticleCategory.MUSIC,
            "imageUrl2",
            555,
            "Nikos Voulgaris",
            true
        ),
    )

    private val articlesMapper: ArticlesPresentationMapper = mock()

    private val articlesPresentation: List<ArticlePresentation> = mock()
    private val emptyArticles: List<Article> = listOf()
    private val successInput = Result.success(articles)
    private val emptyInput = Result.success(emptyArticles)
    private val errorInput = Result.failure<List<Article>>(RuntimeException("error"))

    @Test
    @Ignore("They fail due to Kotlin Result known issue")
    fun mapsSuccessfulInputToSuccessfulState() {
        instantiateMapper()

        val actual = mapper(successInput)

        assertEquals(HomeState.Success(articlesPresentation), actual)
    }

    @Test
    @Ignore("They fail due to Kotlin Result known issue")
    fun mapsEmptyInputToEmptyState() {
        instantiateMapper()

        val actual = mapper(emptyInput)

        assertEquals(HomeState.Empty, actual)
    }

    @Test
    @Ignore("They fail due to Kotlin Result known issue")
    fun mapsErrorInputToErrorState() {
        instantiateMapper()

        val actual = mapper(errorInput)

        assertEquals(HomeState.Error, actual)
    }

    private fun instantiateMapper() {
        whenever(articlesMapper.invoke(articles)).thenReturn(articlesPresentation)

        mapper = HomeStateMapper(articlesMapper)
    }



}