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

import com.petros.efthymiou.domain.entities.Article
import com.petros.efthymiou.domain.entities.plain.ArticleCategory
import com.petros.efthymiou.presentation.ArticlePresentation
import com.petros.efthymiou.presentation.ArticlesPresentationMapper
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ArticlesPresentationMapperShould {

    private val mapper = ArticlesPresentationMapper()

    @Test
    fun mapArticlesToArticlesPresentation() {
        val actual = mapper(input)

        assertEquals(expected, actual)
    }

    private val input = listOf(
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

    private val expected = listOf(
        ArticlePresentation(
            "id1",
            "title",
            "desc",
            "20-05-2000",
            1200 / 200,
            "Sports",
            "imageUrl",
            455,
            "Petros Efthymiou",
            false,
            0.2F
        ),
        ArticlePresentation(
            "id2",
            "title2",
            "desc2",
            "30-06-2001",
            1300 / 200,
            "Music",
            "imageUrl2",
            555,
            "Nikos Voulgaris",
            true,
            1F
        ),
    )
}