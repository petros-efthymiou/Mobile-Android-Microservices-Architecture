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
import com.petros.efthymiou.domain.entities.plain.ArticlesAuthorsLikes
import junit.framework.TestCase.assertEquals
import org.junit.Test
import utils.BaseUnitTest
import utils.articlesPlain
import utils.authorsPlain
import utils.likedIds


class ArticlesAuthorsLikesMapperShould: BaseUnitTest() {

    private val sut = ArticlesAuthorsLikesMapper()

    @Test
    fun mapArticlesData() {
        val actual = sut(articlesPlain, authorsPlain, likedIds)

        assertEquals(expected(), actual)
    }

    private fun expected() = ArticlesAuthorsLikes(
        articlesPlain,
        authorsPlain,
        likedIds
    )
}