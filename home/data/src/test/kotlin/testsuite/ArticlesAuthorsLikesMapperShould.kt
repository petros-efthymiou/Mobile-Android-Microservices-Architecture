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

import com.petros.efthymiou.data.ArticlesAuthorsLikesMapper
import com.petros.efthymiou.domain.entities.plain.ArticlePlain
import com.petros.efthymiou.domain.entities.plain.ArticlesAuthorsLikes
import com.petros.efthymiou.domain.entities.plain.AuthorPlain
import junit.framework.TestCase.assertEquals
import org.junit.Test
import utils.BaseUnitTest

class ArticlesAuthorsLikesMapperShould: BaseUnitTest() {

    private val mapper = ArticlesAuthorsLikesMapper()

    @Test
    fun mapArticlesData() {
        val actual = mapper(articlesPlain(), authors(), likedIds())

        assertEquals(expected(), actual)
    }

    private fun expected() = ArticlesAuthorsLikes(
        articlesPlain(),
        authors(),
        likedIds()
    )

    private fun likedIds() = listOf(
        "id",
        "id1"
    )

    private fun authors() = listOf(
        AuthorPlain(
            "authorId1",
            "Petros Efthymiou",
            200
        )
    )

    private fun articlesPlain() = listOf(
        ArticlePlain(
            "id",
            "title",
            "desc",
            "date",
            2000,
            "sports",
            "imageUrl",
            200,
            "authorId1"
        )
    )
}