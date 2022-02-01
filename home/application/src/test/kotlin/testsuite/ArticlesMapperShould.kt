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
import com.petros.efthymiou.domain.entities.mappers.ArticlesMapper
import com.petros.efthymiou.domain.entities.plain.ArticleCategory
import com.petros.efthymiou.domain.entities.plain.ArticlePlain
import com.petros.efthymiou.domain.entities.plain.ArticlesAuthorsLikes
import com.petros.efthymiou.domain.entities.plain.AuthorPlain
import junit.framework.TestCase.assertEquals
import org.junit.Test
import utils.BaseUnitTest

class ArticlesMapperShould : BaseUnitTest() {

    private val input = fakeArticlesAuthorsLikes()

    private val expectedOutput = expectedOutput()


    @Test
    fun mapDataToDomain() {
        val mapper = ArticlesMapper()

        val output = mapper(input)

        assertEquals(expectedOutput, output)
    }


    private fun fakeArticlesAuthorsLikes() = ArticlesAuthorsLikes(
        articlesPlain = listOf(
            ArticlePlain(
                "id1",
                "title",
                "desc",
                "date",
                1200,
                "sports",
                "imageUrl",
                455,
                "authorId1"
            ),
            ArticlePlain(
                "id2",
                "title2",
                "desc2",
                "date2",
                1300,
                "music",
                "imageUrl2",
                555,
                "authorId2"
            ),
        ),
        authorsPlain = listOf(
            AuthorPlain(
                "authorId1",
                "Petros Efthymiou",
                100
            ),
            AuthorPlain(
                "authorId2",
                "Nikos Voulgaris",
                99
            )
        ),
        likedArticlesIds = listOf(
            "id1"
        )
    )

    private fun expectedOutput() = listOf(
        Article(
            "id1",
            "title",
            "desc",
            "date",
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
            "date2",
            1300 / 200,
            ArticleCategory.MUSIC,
            "imageUrl2",
            555,
            "Nikos Voulgaris",
            true
        ),
    )
}